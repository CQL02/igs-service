package com.wif3006.igsservice.compliance.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "TAX")
public class TaxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TAX_NAME", nullable = false)
    private String taxName;

    @Column(name = "RATE", nullable = false)
    private Double rate;
}
