package com.wif3006.igsservice.customer.controllers;

import com.wif3006.igsservice.customer.models.CustomerModel;
import com.wif3006.igsservice.customer.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    ResponseEntity<Boolean> addCustomer(@RequestBody CustomerModel customerModel) {
        return ResponseEntity.ok(customerService.addCustomer(customerModel));
    }

    @PutMapping("/update")
    ResponseEntity<Boolean> updateCustomer(@RequestParam("id") Long id, @RequestBody CustomerModel customerModel) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerModel));
    }

    @DeleteMapping("/delete")
    ResponseEntity<Boolean> deleteCustomer(@RequestParam("id") Long id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }

    @GetMapping("/get-customer-by-id")
    ResponseEntity<CustomerModel> getCustomerById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/get-all-customers")
    ResponseEntity<List<CustomerModel>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
