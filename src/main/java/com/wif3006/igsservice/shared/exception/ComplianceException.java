package com.wif3006.igsservice.shared.exception;

import com.wif3006.igsservice.shared.constant.ComplianceErrorConstant;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ComplianceException extends RuntimeException {
    private final String code;

    public ComplianceException(ComplianceErrorConstant error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    @Override
    public String getMessage() {
        return String.format("Error Code: %s, Message: %s", code, super.getMessage());
    }
}
