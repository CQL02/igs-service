package com.wif3006.igsservice.shared.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductErrorConstant {
    INVALID_PRODUCT("3001", "Invalid Product"),
    PRODUCT_NOT_FOUND("3002", "Product not found");

    private final String code;
    private final String message;
}
