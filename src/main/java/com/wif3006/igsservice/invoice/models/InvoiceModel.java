package com.wif3006.igsservice.invoice.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvoiceModel {
    private String id;
    private Long merId;
    private Long cusId;
    private Long disId;
    private Long taxId;
    private Date createdOn;
    private Double subtotal;
    private Double totalDiscount;
    private Double totalTax;
    private Double totalPrice;
    private List<InvoiceItemModel> invoiceItemModelList;
}
