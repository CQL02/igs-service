package com.wif3006.igsservice.invoice.models;

import lombok.Data;

@Data
public class InvoiceItemModel {
    private Long id;
    private String invId;
    private Long proId;
    private Integer quantity;
    private String description;
    private Double unitPrice;
    private String productName;
}
