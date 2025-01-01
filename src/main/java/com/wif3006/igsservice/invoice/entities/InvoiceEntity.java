package com.wif3006.igsservice.invoice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "INVOICE")
public class InvoiceEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Column(name = "MER_ID", nullable = false)
    private Long merId;

    @Column(name = "CUS_ID", nullable = false)
    private Long cusId;

    @Column(name = "DIS_ID")
    private Long disId;

    @Column(name = "TAX_ID")
    private Long taxId;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "SUBTOTAL")
    private Double subtotal;

    @Column(name = "TOTAL_DISCOUNT")
    private Double totalDiscount;

    @Column(name = "TOTAL_TAX")
    private Double totalTax;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;
}
