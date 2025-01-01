package com.wif3006.igsservice.merchant.services;

import com.wif3006.igsservice.merchant.models.MerchantModel;

import java.util.List;

public interface MerchantService {
    Boolean addMerchant(MerchantModel merchantModel);
    Boolean updateMerchant(Long id, MerchantModel merchantModel);
    Boolean deleteMerchant(Long id);
    MerchantModel getMerchantById(Long id);
    List<MerchantModel> getAllMerchants();
}
