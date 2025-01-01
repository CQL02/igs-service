package com.wif3006.igsservice.merchant.repositories;

import com.wif3006.igsservice.merchant.entities.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {

    MerchantEntity findMerchantEntityById(Long id);
}
