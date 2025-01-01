package com.wif3006.igsservice.invoice.models;

import lombok.Data;

@Data
public class CalculationResponseModel {
    private Double subtotal;
    private Double totalTax;
    private Double totalDiscount;
    private Double totalPrice;
}
