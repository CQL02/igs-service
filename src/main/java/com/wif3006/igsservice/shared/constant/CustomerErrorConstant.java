package com.wif3006.igsservice.shared.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerErrorConstant {
    INVALID_CUSTOMER("4001", "Invalid customer ID"),
    CUSTOMER_NOT_FOUND("4002", "Customer not found");

    private final String code;
    private final String message;
}
