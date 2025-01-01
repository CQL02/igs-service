package com.wif3006.igsservice.invoice.services;

import com.wif3006.igsservice.invoice.models.CalculationModel;
import com.wif3006.igsservice.invoice.models.CalculationResponseModel;

public interface CalculatorService {
    CalculationResponseModel calculateFinalPrice(CalculationModel calculationModel);

}
