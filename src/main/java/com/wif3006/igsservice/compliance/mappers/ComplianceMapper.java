package com.wif3006.igsservice.compliance.mappers;

import com.wif3006.igsservice.compliance.entities.DiscountEntity;
import com.wif3006.igsservice.compliance.entities.TaxEntity;
import com.wif3006.igsservice.compliance.models.DiscountModel;
import com.wif3006.igsservice.compliance.models.TaxModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComplianceMapper {
    DiscountModel discountEntityToDiscountModel(DiscountEntity discountEntity);
    List<DiscountModel> discountEntityListToDiscountModelList(List<DiscountEntity> discountEntityList);

    DiscountEntity discountModelToDiscountEntity(DiscountModel discountModel);
    List<DiscountEntity> discountModelListToDiscountEntityList(List<DiscountModel> discountModelList);

    TaxModel taxEntityToTaxModel(TaxEntity taxEntity);
    List<TaxModel> taxEntityListToTaxModelList(List<TaxEntity> taxEntityList);

    TaxEntity taxModelToTaxEntity(TaxModel taxModel);
    List<TaxEntity> taxModelListToTaxEntityList(List<TaxModel> taxModelList);
}
