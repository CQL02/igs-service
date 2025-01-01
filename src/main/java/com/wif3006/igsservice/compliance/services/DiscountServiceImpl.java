package com.wif3006.igsservice.compliance.services;

import com.wif3006.igsservice.compliance.entities.DiscountEntity;
import com.wif3006.igsservice.compliance.mappers.ComplianceMapper;
import com.wif3006.igsservice.compliance.models.DiscountModel;
import com.wif3006.igsservice.compliance.repositories.DiscountRepository;
import com.wif3006.igsservice.shared.constant.ComplianceErrorConstant;
import com.wif3006.igsservice.shared.exception.ComplianceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ComplianceMapper complianceMapper;

    @Override
    public Boolean addDiscount(DiscountModel discountModel) {
        DiscountEntity entity = complianceMapper.discountModelToDiscountEntity(discountModel);
        try {
           discountRepository.save(entity);
        } catch (Exception e) {
            throw new ComplianceException(ComplianceErrorConstant.INVALID_DISCOUNT);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateDiscount(Long id, DiscountModel discountModel) {
        DiscountEntity discountEntity = complianceMapper.discountModelToDiscountEntity(discountModel);
        discountEntity.setId(id);

        try{
            discountRepository.save(discountEntity);
        } catch (Exception e) {
            throw new ComplianceException(ComplianceErrorConstant.INVALID_DISCOUNT);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteDiscount(Long id) {
        try {
            boolean validDiscount = discountRepository.existsById(id);
            if (!validDiscount) {
                throw new ComplianceException(ComplianceErrorConstant.DISCOUNT_NOT_FOUND);
            }

            discountRepository.deleteById(id);
        } catch (Exception e) {
            throw new ComplianceException(ComplianceErrorConstant.INVALID_DISCOUNT);
        }

        return Boolean.TRUE;
    }

    @Override
    public DiscountModel getDiscountById(Long id) {
        DiscountEntity discountEntity = discountRepository
                .findById(id)
                .orElseThrow(() -> new ComplianceException(ComplianceErrorConstant.DISCOUNT_NOT_FOUND));

        return complianceMapper.discountEntityToDiscountModel(discountEntity);
    }

    @Override
    public List<DiscountModel> getAllDiscounts() {
        List<DiscountEntity> discountEntities = discountRepository.findAll();
        return complianceMapper.discountEntityListToDiscountModelList(discountEntities);
    }
}
