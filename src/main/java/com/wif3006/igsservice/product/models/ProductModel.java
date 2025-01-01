package com.wif3006.igsservice.product.models;

import lombok.Data;

@Data
public class ProductModel {
    private Long id;
    private String productName;
    private double unitPrice;
}
