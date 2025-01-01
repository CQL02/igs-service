package com.wif3006.igsservice.shared.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InvoiceErrorConstant {
    INVALID_INVOICE("2001", "Invalid Invoice ID"),
    INVOICE_NOT_FOUND("2002", "Invoice not found");

    private final String code;
    private final String message;
}
