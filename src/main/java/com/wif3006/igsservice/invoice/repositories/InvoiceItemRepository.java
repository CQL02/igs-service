package com.wif3006.igsservice.invoice.repositories;

import com.wif3006.igsservice.invoice.entities.InvoiceItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;;

import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItemEntity, Long> {
    @Transactional
    int deleteAllByInvId(String invId);

    List<InvoiceItemEntity> findAllByInvId(String invId);
}
