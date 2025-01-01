package com.wif3006.igsservice.invoice.services;

import com.wif3006.igsservice.invoice.models.CalculationModel;
import com.wif3006.igsservice.invoice.models.CalculationResponseModel;
import com.wif3006.igsservice.shared.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService{
    @Override
    public CalculationResponseModel calculateFinalPrice(CalculationModel calculationModel) {
        double subtotal = calculationModel.getInvoiceItemModelList()
                .stream()
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
        BigDecimal roundedSubtotal = BigDecimal.valueOf(subtotal).setScale(2, RoundingMode.UP);


        double totalTax = 0.0;
        if(ObjectUtils.isNotEmpty(calculationModel.getTaxModel()) && calculationModel.getTaxModel().getRate() != null){
            totalTax = subtotal * calculationModel.getTaxModel().getRate() / 100;
        }
        BigDecimal roundedTotalTax = BigDecimal.valueOf(totalTax).setScale(2, RoundingMode.UP);

        double totalDiscount = 0.0;
        if(ObjectUtils.isNotEmpty(calculationModel.getDiscountModel()) && calculationModel.getDiscountModel().getRate() != null){
            totalDiscount = (subtotal + totalTax) * calculationModel.getDiscountModel().getRate() / 100;
        }
        BigDecimal roundedTotalDiscount = BigDecimal.valueOf(totalDiscount).setScale(2, RoundingMode.UP);

        BigDecimal totalPrice = roundedSubtotal.add(roundedTotalTax).subtract(roundedTotalDiscount);

        CalculationResponseModel calculationResponseModel = new CalculationResponseModel();
        calculationResponseModel.setSubtotal(roundedSubtotal.doubleValue());
        calculationResponseModel.setTotalTax(roundedTotalTax.doubleValue());
        calculationResponseModel.setTotalDiscount(roundedTotalDiscount.doubleValue());
        calculationResponseModel.setTotalPrice(totalPrice.setScale(2, RoundingMode.UP).doubleValue());

        return calculationResponseModel;
    }
}
