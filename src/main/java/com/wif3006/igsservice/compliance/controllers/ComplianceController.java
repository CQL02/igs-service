package com.wif3006.igsservice.compliance.controllers;

import com.wif3006.igsservice.compliance.models.DiscountModel;
import com.wif3006.igsservice.compliance.models.TaxModel;
import com.wif3006.igsservice.compliance.services.DiscountService;
import com.wif3006.igsservice.compliance.services.TaxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compliance")
@RequiredArgsConstructor
public class ComplianceController {

    private final TaxService taxService;
    private final DiscountService discountService;

    // Tax
    @PostMapping("/tax/create")
    ResponseEntity<Boolean> addTax(@RequestBody TaxModel taxModel) {
        return ResponseEntity.ok(taxService.addTax(taxModel));
    }

    @PutMapping("/tax/update")
    ResponseEntity<Boolean> updateTax(@RequestParam("id") Long id, @RequestBody TaxModel taxModel) {
        return ResponseEntity.ok(taxService.updateTax(id, taxModel));
    }

    @DeleteMapping("/tax/delete")
    ResponseEntity<Boolean> deleteTax(@RequestParam("id") Long id) {
        return ResponseEntity.ok(taxService.deleteTax(id));
    }

    @GetMapping("/tax/get-tax-by-id")
    ResponseEntity<TaxModel> getTaxById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(taxService.getTaxById(id));
    }

    @GetMapping("/tax/get-all-taxes")
    ResponseEntity<List<TaxModel>> getAllTaxes() {
        return ResponseEntity.ok(taxService.getAllTaxes());
    }

    //Discount
    @PostMapping("/discount/create")
    ResponseEntity<Boolean> addDiscount(@RequestBody DiscountModel discountModel) {
        return ResponseEntity.ok(discountService.addDiscount(discountModel));
    }

    @PutMapping("/discount/update")
    ResponseEntity<Boolean> updateDiscount(@RequestParam("id") Long id, @RequestBody DiscountModel discountModel) {
        return ResponseEntity.ok(discountService.updateDiscount(id, discountModel));
    }

    @DeleteMapping("/discount/delete")
    ResponseEntity<Boolean> deleteDiscount(@RequestParam("id") Long id) {
        return ResponseEntity.ok(discountService.deleteDiscount(id));
    }

    @GetMapping("/discount/get-discount-by-id")
    ResponseEntity<DiscountModel> getDiscountById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(discountService.getDiscountById(id));
    }

    @GetMapping("/discount/get-all-discounts")
    ResponseEntity<List<DiscountModel>> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }
}
