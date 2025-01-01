package com.wif3006.igsservice.compliance.services;

import com.wif3006.igsservice.compliance.models.TaxModel;

import java.util.List;

public interface TaxService {
    Boolean addTax(TaxModel taxModel);
    Boolean updateTax(Long id, TaxModel taxModel);
    Boolean deleteTax(Long id);
    TaxModel getTaxById(Long id);
    List<TaxModel> getAllTaxes();
}
