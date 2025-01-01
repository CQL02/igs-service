package com.wif3006.igsservice.compliance.services;

import com.wif3006.igsservice.compliance.entities.TaxEntity;
import com.wif3006.igsservice.compliance.mappers.ComplianceMapper;
import com.wif3006.igsservice.compliance.models.TaxModel;
import com.wif3006.igsservice.compliance.repositories.TaxRepository;
import com.wif3006.igsservice.shared.constant.ComplianceErrorConstant;
import com.wif3006.igsservice.shared.exception.ComplianceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {

    private final TaxRepository taxRepository;
    private final ComplianceMapper complianceMapper;

    @Override
    public Boolean addTax(TaxModel taxModel) {
        TaxEntity taxEntity = complianceMapper.taxModelToTaxEntity(taxModel);
        try{
            taxRepository.save(taxEntity);
        } catch (Exception e){
            throw new ComplianceException(ComplianceErrorConstant.INVALID_TAX);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateTax(Long id, TaxModel taxModel) {
        TaxEntity updatedEntity = complianceMapper.taxModelToTaxEntity(taxModel);
        updatedEntity.setId(id);

        try{
            taxRepository.save(updatedEntity);
        } catch (Exception e){
            throw new ComplianceException(ComplianceErrorConstant.INVALID_TAX);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteTax(Long id) {
        try {
            boolean validTax = taxRepository.existsById(id);
            if (!validTax) {
                throw new ComplianceException(ComplianceErrorConstant.TAX_NOT_FOUND);
            }
            taxRepository.deleteById(id);
        } catch (Exception e){
            throw new ComplianceException(ComplianceErrorConstant.INVALID_TAX);
        }

        return Boolean.TRUE;
    }

    @Override
    public TaxModel getTaxById(Long id) {
        TaxEntity taxEntity = taxRepository
                .findById(id)
                .orElseThrow(() -> new ComplianceException(ComplianceErrorConstant.TAX_NOT_FOUND));

        return complianceMapper.taxEntityToTaxModel(taxEntity);
    }

    @Override
    public List<TaxModel> getAllTaxes() {
        List<TaxEntity> taxEntities = taxRepository.findAll();
        return complianceMapper.taxEntityListToTaxModelList(taxEntities);
    }
}
