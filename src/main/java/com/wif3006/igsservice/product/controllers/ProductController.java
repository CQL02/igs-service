package com.wif3006.igsservice.product.controllers;

import com.wif3006.igsservice.product.models.ProductModel;
import com.wif3006.igsservice.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    ResponseEntity<Boolean> addProduct(@RequestBody ProductModel productModel) {
        return ResponseEntity.ok(productService.addProduct(productModel));
    }

    @PutMapping("/update")
    ResponseEntity<Boolean> updateProduct(@RequestParam("id") Long id, @RequestBody ProductModel productModel) {
        return ResponseEntity.ok(productService.updateProduct(id, productModel));
    }

    @DeleteMapping("/delete")
    ResponseEntity<Boolean> deleteProduct(@RequestParam("id") Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/get-product-by-id")
    ResponseEntity<ProductModel> getProductById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/get-all-products")
    ResponseEntity<List<ProductModel>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
