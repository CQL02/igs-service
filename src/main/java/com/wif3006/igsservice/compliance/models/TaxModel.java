package com.wif3006.igsservice.compliance.models;

import lombok.Data;

@Data
public class TaxModel {
    private Long id;
    private String taxName;
    private Double rate;
}
