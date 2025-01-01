package com.wif3006.igsservice.merchant.controllers;

import com.wif3006.igsservice.merchant.models.MerchantModel;
import com.wif3006.igsservice.merchant.services.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @PostMapping("/create")
    ResponseEntity<Boolean> createMerchant(@RequestBody MerchantModel merchantModel) {
        return ResponseEntity.ok(merchantService.addMerchant(merchantModel));
    }

    @PutMapping("/update")
    ResponseEntity<Boolean> updateMerchant(@RequestParam("id") Long id, @RequestBody MerchantModel merchantModel) {
        return ResponseEntity.ok(merchantService.updateMerchant(id, merchantModel));
    }

    @DeleteMapping("/delete")
    ResponseEntity<Boolean> deleteMerchant(@RequestParam("id") Long id) {
        return ResponseEntity.ok(merchantService.deleteMerchant(id));
    }

    @GetMapping("/get-merchant-by-id")
    ResponseEntity<MerchantModel> getMerchantById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(merchantService.getMerchantById(id));
    }

    @GetMapping("/get-all-merchants")
    ResponseEntity<List<MerchantModel>> getAllMerchants() {
        return ResponseEntity.ok(merchantService.getAllMerchants());
    }
}
