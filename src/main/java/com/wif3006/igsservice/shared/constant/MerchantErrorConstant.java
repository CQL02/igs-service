package com.wif3006.igsservice.shared.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MerchantErrorConstant {
    INVALID_MERCHANT("2001", "Invalid merchant ID"),
    MERCHANT_NOT_FOUND("2002", "Merchant not found");

    private final String code;
    private final String message;
}
