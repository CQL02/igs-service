package com.wif3006.igsservice.invoice.repositories;

import com.wif3006.igsservice.invoice.entities.InvoiceItemEntity;
import com.wif3006.igsservice.invoice.models.InvoiceItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItemEntity, Long> {
    Boolean deleteAllByInvId(String invId);

    List<InvoiceItemEntity> findAllByInvId(String invId);
}
