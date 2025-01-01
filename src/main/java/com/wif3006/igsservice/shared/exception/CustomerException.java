package com.wif3006.igsservice.shared.exception;

import com.wif3006.igsservice.shared.constant.CustomerErrorConstant;

public class CustomerException extends RuntimeException {
    private final String code;

    public CustomerException(CustomerErrorConstant error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    @Override
    public String getMessage() {
        return String.format("Error Code: %s, Message: %s", code, super.getMessage());
    }
}
