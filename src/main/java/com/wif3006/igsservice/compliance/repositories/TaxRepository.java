package com.wif3006.igsservice.compliance.repositories;

import com.wif3006.igsservice.compliance.entities.TaxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<TaxEntity, Long> {
}
