package com.wif3006.igsservice.invoice.services;

import java.util.Map;

public interface PdfService {
    byte[] generatePdf(String templateName, Map<String, Object> data);
}
