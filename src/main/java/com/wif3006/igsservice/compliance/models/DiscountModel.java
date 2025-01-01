package com.wif3006.igsservice.compliance.models;

import lombok.Data;

@Data
public class DiscountModel {
    private Long id;
    private String discountName;
    private Double rate;
}
