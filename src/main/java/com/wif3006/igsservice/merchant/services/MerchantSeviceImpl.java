package com.wif3006.igsservice.merchant.services;

import com.wif3006.igsservice.shared.constant.MerchantErrorConstant;
import com.wif3006.igsservice.shared.exception.MerchantException;
import com.wif3006.igsservice.merchant.entities.MerchantEntity;
import com.wif3006.igsservice.merchant.mappers.MerchantMapper;
import com.wif3006.igsservice.merchant.models.MerchantModel;
import com.wif3006.igsservice.merchant.repositories.MerchantRepository;
import com.wif3006.igsservice.shared.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantSeviceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;

    @Override
    public Boolean addMerchant(MerchantModel merchantModel) {
        MerchantEntity entity = merchantMapper.merchantModelToMerchantEntity(merchantModel);
        try {
            merchantRepository.save(entity);
        } catch (Exception e) {
            throw new MerchantException(MerchantErrorConstant.INVALID_MERCHANT);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateMerchant(Long id, MerchantModel merchantModel) {
        MerchantEntity updatedEntity = merchantMapper.merchantModelToMerchantEntity(merchantModel);
        updatedEntity.setId(id);

        try {
            merchantRepository.save(updatedEntity);
        } catch (Exception e) {
            throw new MerchantException(MerchantErrorConstant.INVALID_MERCHANT);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteMerchant(Long id) {
        try {
            MerchantEntity merchant = merchantRepository.findMerchantEntityById(id);
            if (ObjectUtils.isEmpty(merchant)) {
                throw new MerchantException(MerchantErrorConstant.MERCHANT_NOT_FOUND);
            }

            merchantRepository.deleteById(id);
        } catch (Exception e) {
            throw new MerchantException(MerchantErrorConstant.INVALID_MERCHANT);
        }
        return Boolean.TRUE;
    }

    @Override
    public MerchantModel getMerchantById(Long id) {
        MerchantEntity merchant = merchantRepository.findMerchantEntityById(id);
        log.info(merchant +"");
        return merchantMapper.merchantEntityToMerchantModel(merchant);
    }

    @Override
    public List<MerchantModel> getAllMerchants() {
        List<MerchantEntity> merchants = merchantRepository.findAll();
        return merchantMapper.merchantEntityListToMerchantModelList(merchants);
    }
}
