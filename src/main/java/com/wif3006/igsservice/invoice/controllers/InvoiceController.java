package com.wif3006.igsservice.invoice.controllers;

import com.wif3006.igsservice.invoice.models.InvoiceItemModel;
import com.wif3006.igsservice.invoice.models.InvoiceModel;
import com.wif3006.igsservice.invoice.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping("/create")
    ResponseEntity<Boolean> addInvoice(@RequestBody InvoiceModel invoiceModel) {
        return ResponseEntity.ok(invoiceService.addInvoice(invoiceModel));
    }

    @PutMapping("/update")
    ResponseEntity<Boolean> updateInvoice(@RequestParam("id") String id, @RequestBody InvoiceModel invoiceModel) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, invoiceModel));
    }

    @DeleteMapping("/delete")
    ResponseEntity<Boolean> deleteInvoice(@RequestParam("id") String id) {
        return ResponseEntity.ok(invoiceService.deleteInvoice(id));
    }

    @GetMapping("/get-all-invoices")
    ResponseEntity<List<InvoiceModel>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoice());
    }

    @PostMapping("/preview-invoice")
    ResponseEntity<Map<String, String>> previewInvoice(@RequestBody InvoiceModel invoiceModel) {
        return ResponseEntity.ok(invoiceService.previewInvoice(invoiceModel));
    }

    @GetMapping("/download-invoice")
    ResponseEntity<byte[]> downloadInvoice(@RequestParam("id") String id) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(invoiceService.downloadInvoice(id));
    }

    @GetMapping("/get-invoice-items-by-inv-id")
    ResponseEntity<List<InvoiceItemModel>> getInvoiceItemsByInvId(@RequestParam("id") String invId) {
        return ResponseEntity.ok(invoiceService.getInvoiceItemsByInvId(invId));
    }
}
