package com.wif3006.igsservice.invoice.services;

import com.wif3006.igsservice.invoice.models.CalculationModel;
import com.wif3006.igsservice.invoice.models.CalculationResponseModel;
import com.wif3006.igsservice.compliance.models.DiscountModel;
import com.wif3006.igsservice.compliance.models.TaxModel;
import com.wif3006.igsservice.compliance.services.DiscountService;
import com.wif3006.igsservice.compliance.services.TaxService;
import com.wif3006.igsservice.customer.models.CustomerModel;
import com.wif3006.igsservice.customer.services.CustomerService;
import com.wif3006.igsservice.invoice.entities.InvoiceEntity;
import com.wif3006.igsservice.invoice.entities.InvoiceItemEntity;
import com.wif3006.igsservice.invoice.mappers.InvoiceMapper;
import com.wif3006.igsservice.invoice.models.InvoiceItemModel;
import com.wif3006.igsservice.invoice.models.InvoiceModel;
import com.wif3006.igsservice.invoice.repositories.InvoiceItemRepository;
import com.wif3006.igsservice.invoice.repositories.InvoiceRepository;
import com.wif3006.igsservice.merchant.models.MerchantModel;
import com.wif3006.igsservice.merchant.services.MerchantService;
import com.wif3006.igsservice.product.models.ProductModel;
import com.wif3006.igsservice.product.services.ProductService;
import com.wif3006.igsservice.shared.constant.InvoiceErrorConstant;
import com.wif3006.igsservice.shared.exception.InvoiceException;
import com.wif3006.igsservice.shared.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;
    private final InvoiceMapper invoiceMapper;
    private final CalculatorService calculatorService;
    private final DiscountService discountService;
    private final TaxService taxService;
    private final CustomerService customerService;
    private final MerchantService merchantService;
    private final PdfService pdfService;
    private final ProductService productService;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Boolean addInvoice(InvoiceModel invoiceModel) {
        try {
            Long nextSequence = jdbcTemplate.queryForObject("SELECT NEXTVAL('INVOICE_SEQ')", Long.class);
            String customId = String.format("INV%09d", nextSequence);

            InvoiceModel calculationDetails = getInvoiceDetails(invoiceModel);

            // Map to InvoiceEntity
            InvoiceEntity invoiceEntity = invoiceMapper.invoiceModelToInvoiceEntity(invoiceModel);
            invoiceEntity.setTotalDiscount(calculationDetails.getTotalDiscount());
            invoiceEntity.setSubtotal(calculationDetails.getSubtotal());
            invoiceEntity.setTotalTax(calculationDetails.getTotalTax());
            invoiceEntity.setTotalPrice(calculationDetails.getTotalPrice());
            invoiceEntity.setCreatedOn(new Date());
            invoiceEntity.setId(customId);

            // Save the invoice entity to generate ID
            invoiceEntity = invoiceRepository.save(invoiceEntity);

            // Map to InvoiceItemEntity and set parent reference
            InvoiceEntity finalInvoiceEntity = invoiceEntity;
            List<InvoiceItemEntity> invoiceItemEntities = invoiceMapper.invoiceItemModelListToInvoiceItemEntityList(calculationDetails.getInvoiceItemModelList())
                    .stream()
                    .peek(item -> item.setInvId(finalInvoiceEntity.getId()))
                    .toList();

            // Save all invoice items
            invoiceItemRepository.saveAll(invoiceItemEntities);
        } catch (Exception e) {
            throw new InvoiceException(InvoiceErrorConstant.INVALID_INVOICE);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateInvoice(String id, InvoiceModel invoiceModel) {
        try {
            InvoiceModel calculationDetails = getInvoiceDetails(invoiceModel);

            InvoiceEntity invoiceEntity = invoiceMapper.invoiceModelToInvoiceEntity(invoiceModel);
            invoiceEntity.setId(id);
            invoiceEntity.setTotalDiscount(calculationDetails.getTotalDiscount());
            invoiceEntity.setSubtotal(calculationDetails.getSubtotal());
            invoiceEntity.setTotalTax(calculationDetails.getTotalTax());
            invoiceEntity.setTotalPrice(calculationDetails.getTotalPrice());

            List<InvoiceItemEntity> invoiceItemEntities =
                    invoiceMapper.invoiceItemModelListToInvoiceItemEntityList(invoiceModel.getInvoiceItemModelList());
            invoiceItemEntities.forEach(item -> item.setInvId(id));

            invoiceRepository.save(invoiceEntity);
            invoiceItemRepository.saveAll(invoiceItemEntities);
        } catch (Exception e) {
            throw new InvoiceException(InvoiceErrorConstant.INVALID_INVOICE);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteInvoice(String id) {
        try {
            boolean validInvoice = invoiceRepository.existsById(id);
            if (!validInvoice) {
                throw new InvoiceException(InvoiceErrorConstant.INVALID_INVOICE);
            }

            invoiceRepository.deleteById(id);
            log.info("run this 1");
            invoiceItemRepository.deleteAllByInvId(id);
            log.info("run this 2");
        } catch (Exception e) {
            throw new InvoiceException(InvoiceErrorConstant.INVALID_INVOICE);
        }
        return Boolean.TRUE;
    }

    @Override
    public List<InvoiceModel> getAllInvoice() {
        List<InvoiceEntity> invoiceEntityList = invoiceRepository.findAll();
        return invoiceMapper.invoiceEntityListToInvoiceModelList(invoiceEntityList);
    }

    @Override
    public Map<String, String> previewInvoice(InvoiceModel invoiceModel) {
        MerchantModel merchantModel = merchantService.getMerchantById(invoiceModel.getMerId());
        CustomerModel customerModel = customerService.getCustomerById(invoiceModel.getCusId());

        InvoiceModel calculationResult = getInvoiceDetails(invoiceModel);

        Map<String, Object> data = setPdfData(merchantModel, customerModel, calculationResult);
        data.put("createDate", getDateString(LocalDateTime.now()));

        log.info(invoiceModel.getId() + "");
        String previewInvoiceNo = invoiceModel.getId() != null ? invoiceModel.getId() : String.format("INV%09d", peekNextSequenceValue("invoice_seq"));
        data.put("invoiceNo", previewInvoiceNo);

        byte[] pdfBytes = pdfService.generatePdf("invoice.ftl", data);

        String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);

        Map<String, String> response = new HashMap<>();
        response.put("pdf", base64Pdf);
        return response;
    }

    @Override
    public byte[] downloadInvoice(String id) {
        InvoiceEntity invoiceEntity = invoiceRepository.findById(id).orElseThrow(
                () -> new InvoiceException(InvoiceErrorConstant.INVOICE_NOT_FOUND)
        );

        List<InvoiceItemEntity> invoiceItemEntityList = invoiceItemRepository.findAllByInvId(id);
        List<InvoiceItemModel> invoiceItemModelList = invoiceMapper.invoiceItemEntityListToInvoiceItemModelList(invoiceItemEntityList);
        InvoiceModel invoiceModel = invoiceMapper.invoiceEntityToInvoiceModel(invoiceEntity);
        invoiceModel.setInvoiceItemModelList(invoiceItemModelList);

        MerchantModel merchantModel = merchantService.getMerchantById(invoiceEntity.getMerId());
        CustomerModel customerModel = customerService.getCustomerById(invoiceEntity.getCusId());

        InvoiceModel calculationResult = getInvoiceDetails(invoiceModel);

        Map<String, Object> data = setPdfData(merchantModel, customerModel, calculationResult);
        data.put("createDate", getDateString(invoiceEntity.getCreatedOn().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        data.put("invoiceNo", invoiceEntity.getId());

        return pdfService.generatePdf("invoice.ftl", data);
    }

    private Map<String, Object> setPdfData (MerchantModel merchantModel, CustomerModel customerModel, InvoiceModel calculationResult) {
        Map<String, Object> data = new HashMap<>();
        data.put("merchantCompanyName", merchantModel.getCompanyName());
        data.put("merchantRegistrationNo", merchantModel.getRegistrationNo());
        data.put("merchantCompanyAddress", getAddress(
                merchantModel.getAddrLine1(),
                merchantModel.getAddrLine2(),
                merchantModel.getPostcode(),
                merchantModel.getCity(),
                merchantModel.getState(),
                merchantModel.getCountry()));
        data.put("merchantTelNo", merchantModel.getTel());
        data.put("merchantFaxNo", merchantModel.getFax());
        data.put("merchantEmail", merchantModel.getEmail());
        data.put("bankName", merchantModel.getBankName());
        data.put("bankAccountNo", merchantModel.getBankAccNo());

        //CustomerData
        data.put("customerCompanyName", customerModel.getCompanyName());
        data.put("customerCompanyAddress", getAddress(
                customerModel.getAddrLine1(),
                customerModel.getAddrLine2(),
                customerModel.getPostcode(),
                customerModel.getCity(),
                customerModel.getState(),
                customerModel.getCountry()));
        data.put("customerPic", customerModel.getPic());
        data.put("customerTel", customerModel.getTel());
        data.put("customerFax", customerModel.getFax());
        data.put("customerEmail", customerModel.getEmail());

        List<Map<String, Object>> items = new ArrayList<>();
        calculationResult.getInvoiceItemModelList().forEach(item -> {
            Map<String, Object> itemMap = Map.of(
                    "description", getDescription(item.getProductName(), item.getDescription()),
                    "quantity", item.getQuantity(),
                    "unitRate", String.format("%.2f", item.getUnitPrice()),
                    "totalAmount", String.format("%.2f", item.getQuantity() * item.getUnitPrice()));
            items.add(itemMap);
        });
        data.put("items", items);

        data.put("subtotal", String.format("%.2f", calculationResult.getSubtotal()));
        data.put("totalDiscount", String.format("%.2f", calculationResult.getTotalDiscount()));
        data.put("totalTax", String.format("%.2f", calculationResult.getTotalTax()));
        data.put("totalAmount", String.format("%.2f", calculationResult.getTotalPrice()));
        return data;
    }

    private String getAddress(String addrLine1, String addrLine2, String postcode, String city, String state, String country) {
        StringBuilder line1 = new StringBuilder();
        StringBuilder line2 = new StringBuilder();

        line1.append(addrLine1);

        if (addrLine2 != null && !addrLine2.isEmpty()) {
            line1.append(", ").append(addrLine2);
        }

        line2.append(postcode).append(" ").append(city).append(", ").append(state).append(", ").append(country);
        return line1 + "<br />" + line2;
    }

    private String getDescription(String item, String description) {
        StringBuilder sb = new StringBuilder();
        sb.append("<strong>").append(item).append("</strong>");

        if (description != null && !description.isEmpty()) {
            description = description.replace("\n", "<br />");
            sb.append("<br />").append(description);
        }
        return sb.toString();
    }

    private String getDateString(LocalDateTime dateTime) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(myFormatObj);
    }

    private Long peekNextSequenceValue(String sequenceName) {
        String sql = "SELECT last_value + increment_by AS next_value " +
                "FROM pg_sequences " +
                "WHERE schemaname = 'public' AND sequencename = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{sequenceName}, Long.class);
    }

    private InvoiceModel getInvoiceDetails(InvoiceModel invoiceModel) {
        DiscountModel discountModel = invoiceModel.getDisId() != null
                ? discountService.getDiscountById(invoiceModel.getDisId())
                : new DiscountModel();

        TaxModel taxModel = invoiceModel.getTaxId() != null
                ? taxService.getTaxById(invoiceModel.getTaxId())
                : new TaxModel();

        Map<Long, ProductModel> productMap = productService.getProductsByIds(
                invoiceModel.getInvoiceItemModelList()
                        .stream()
                        .map(InvoiceItemModel::getProId)
                        .toList()
        ).stream().collect(Collectors.toMap(ProductModel::getId, product -> product));

        List<InvoiceItemModel> invoiceItemModelList = invoiceModel.getInvoiceItemModelList()
                .stream()
                .peek(item -> {
                    ProductModel product = productMap.get(item.getProId());
                    if (product != null) {
                        item.setProductName(product.getProductName());
                        item.setUnitPrice(product.getUnitPrice());
                    }
                })
                .toList();

        // Perform calculation
        CalculationModel calculationModel = new CalculationModel();
        calculationModel.setDiscountModel(discountModel);
        calculationModel.setTaxModel(taxModel);
        calculationModel.setInvoiceItemModelList(invoiceItemModelList);

        CalculationResponseModel calculationResponseModel = calculatorService.calculateFinalPrice(calculationModel);

        invoiceModel.setSubtotal(calculationResponseModel.getSubtotal());
        invoiceModel.setTotalDiscount(calculationResponseModel.getTotalDiscount());
        invoiceModel.setTotalTax(calculationResponseModel.getTotalTax());
        invoiceModel.setTotalPrice(calculationResponseModel.getTotalPrice());
        invoiceModel.setInvoiceItemModelList(invoiceItemModelList);
        return invoiceModel;
    }


    @Override
    public List<InvoiceItemModel> getInvoiceItemsByInvId(String id) {
        return invoiceMapper.invoiceItemEntityListToInvoiceItemModelList(
                invoiceItemRepository.findAllByInvId(id)
        );
    }
}
