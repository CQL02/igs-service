package com.wif3006.igsservice.shared.exception;

import com.wif3006.igsservice.shared.constant.ProductErrorConstant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductException extends RuntimeException {
    private final String code;

    public ProductException(ProductErrorConstant error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    @Override
    public String getMessage() {
        return String.format("Error Code: %s, Message: %s", code, super.getMessage());
    }
}
