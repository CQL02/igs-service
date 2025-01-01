package com.wif3006.igsservice.compliance.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "DISCOUNT")
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DISCOUNT_NAME", nullable = false)
    private String discountName;

    @Column(name = "RATE", nullable = false)
    private Double rate;
}
