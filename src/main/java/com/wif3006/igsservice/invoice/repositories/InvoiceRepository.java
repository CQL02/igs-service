package com.wif3006.igsservice.invoice.repositories;

import com.wif3006.igsservice.invoice.entities.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, String> {
}
