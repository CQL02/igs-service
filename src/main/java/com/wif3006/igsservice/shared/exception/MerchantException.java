package com.wif3006.igsservice.shared.exception;

import com.wif3006.igsservice.shared.constant.MerchantErrorConstant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class MerchantException extends RuntimeException {
    private final String code;

    public MerchantException(MerchantErrorConstant error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    @Override
    public String getMessage() {
        return String.format("Error Code: %s, Message: %s", code, super.getMessage());
    }
}
