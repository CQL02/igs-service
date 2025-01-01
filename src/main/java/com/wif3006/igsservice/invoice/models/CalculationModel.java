package com.wif3006.igsservice.invoice.models;

import com.wif3006.igsservice.compliance.models.DiscountModel;
import com.wif3006.igsservice.compliance.models.TaxModel;
import lombok.Data;

import java.util.List;

@Data
public class CalculationModel {
    private TaxModel taxModel;
    private DiscountModel discountModel;
    private List<InvoiceItemModel> invoiceItemModelList;
}
