package com.wif3006.igsservice.compliance.services;

import com.wif3006.igsservice.compliance.models.DiscountModel;

import java.util.List;

public interface DiscountService {
    Boolean addDiscount(DiscountModel discountModel);
    Boolean updateDiscount(Long id, DiscountModel discountModel);
    Boolean deleteDiscount(Long id);
    DiscountModel getDiscountById(Long id);
    List<DiscountModel> getAllDiscounts();
}
