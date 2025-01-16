package com.wif3006.igsservice.invoice.mappers;

import com.wif3006.igsservice.invoice.entities.InvoiceEntity;
import com.wif3006.igsservice.invoice.entities.InvoiceItemEntity;
import com.wif3006.igsservice.invoice.models.InvoiceItemModel;
import com.wif3006.igsservice.invoice.models.InvoiceModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceModel invoiceEntityToInvoiceModel(InvoiceEntity invoiceEntity);
    List<InvoiceModel> invoiceEntityListToInvoiceModelList(List<InvoiceEntity> invoiceEntityList);

    InvoiceEntity invoiceModelToInvoiceEntity(InvoiceModel invoiceModel);

    InvoiceItemModel invoiceItemEntityToInvoiceItemModel(InvoiceItemEntity invoiceItemEntity);
    List<InvoiceItemModel> invoiceItemEntityListToInvoiceItemModelList(List<InvoiceItemEntity> invoiceItemEntityList);

    InvoiceItemEntity invoiceItemModelToInvoiceItemEntity(InvoiceItemModel invoiceItemModel);
    List<InvoiceItemEntity> invoiceItemModelListToInvoiceItemEntityList(List<InvoiceItemModel> invoiceItemModelList);
}
