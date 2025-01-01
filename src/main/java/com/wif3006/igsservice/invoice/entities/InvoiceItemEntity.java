package com.wif3006.igsservice.invoice.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "INVOICE_ITEM")
public class InvoiceItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "INV_ID", nullable = false)
    private String invId;

    @Column(name = "PRO_ID", nullable = false)
    private Long proId;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "DESCRIPTION")
    private String description;
}
