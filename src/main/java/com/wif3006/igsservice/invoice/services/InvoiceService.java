package com.wif3006.igsservice.invoice.services;

import com.wif3006.igsservice.invoice.models.InvoiceItemModel;
import com.wif3006.igsservice.invoice.models.InvoiceModel;

import java.util.List;
import java.util.Map;

public interface InvoiceService {
    Boolean addInvoice(InvoiceModel invoiceModel);
    Boolean updateInvoice(String id, InvoiceModel invoiceModel);
    Boolean deleteInvoice(String id);
    List<InvoiceModel> getAllInvoice();
    Map<String, String> previewInvoice(InvoiceModel invoiceModel);
    byte[] downloadInvoice(String id);

    List<InvoiceItemModel> getInvoiceItemsByInvId(String id);
}
