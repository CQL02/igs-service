package com.wif3006.igsservice.product.repositories;

import com.wif3006.igsservice.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
