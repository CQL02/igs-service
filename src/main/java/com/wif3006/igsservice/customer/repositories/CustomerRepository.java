package com.wif3006.igsservice.customer.repositories;

import com.wif3006.igsservice.customer.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
