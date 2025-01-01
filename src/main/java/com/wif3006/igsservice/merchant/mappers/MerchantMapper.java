package com.wif3006.igsservice.merchant.mappers;

import com.wif3006.igsservice.merchant.entities.MerchantEntity;
import com.wif3006.igsservice.merchant.models.MerchantModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    MerchantModel merchantEntityToMerchantModel(MerchantEntity merchantEntity);

    List<MerchantModel> merchantEntityListToMerchantModelList(List<MerchantEntity> userEntities);

    MerchantEntity merchantModelToMerchantEntity(MerchantModel merchantModel);

    List<MerchantEntity> merchantModelListToMerchantEntityList(List<MerchantModel> merchantModelList);
}
