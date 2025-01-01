package com.wif3006.igsservice.shared.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ComplianceErrorConstant {
    INVALID_DISCOUNT("5001", "Invalid Discount"),
    DISCOUNT_NOT_FOUND("5002", "Discount not found"),
    INVALID_TAX("5003", "Invalid Tax"),
    TAX_NOT_FOUND("5004", "Tax not found");

    private final String code;
    private final String message;
}
