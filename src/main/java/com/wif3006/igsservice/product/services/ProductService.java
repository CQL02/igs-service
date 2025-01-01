package com.wif3006.igsservice.product.services;

import com.wif3006.igsservice.product.models.ProductModel;

import java.util.List;

public interface ProductService {
    Boolean addProduct(ProductModel productModel);
    Boolean updateProduct(Long id, ProductModel productModel);
    Boolean deleteProduct(Long id);
    ProductModel getProductById(Long id);
    List<ProductModel> getAllProducts();
    List<ProductModel> getProductsByIds(List<Long> ids);
}
