package com.wif3006.igsservice.product.services;

import com.wif3006.igsservice.product.entities.ProductEntity;
import com.wif3006.igsservice.product.mappers.ProductMapper;
import com.wif3006.igsservice.product.models.ProductModel;
import com.wif3006.igsservice.product.repositories.ProductRepository;
import com.wif3006.igsservice.shared.constant.ProductErrorConstant;
import com.wif3006.igsservice.shared.exception.ProductException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Boolean addProduct(ProductModel productModel) {
        ProductEntity entity = productMapper.productModelToProductEntity(productModel);
        try{
            log.info(entity + "");
            productRepository.save(entity);
        } catch (Exception e) {
            throw new ProductException(ProductErrorConstant.INVALID_PRODUCT);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean updateProduct(Long id, ProductModel productModel) {
        ProductEntity updatedEntity = productMapper.productModelToProductEntity(productModel);
        updatedEntity.setId(id);

        try {
            productRepository.save(updatedEntity);
        } catch (Exception e) {
            throw new ProductException(ProductErrorConstant.INVALID_PRODUCT);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        try {
            boolean validProduct = productRepository.findById(id).isEmpty();
            if (validProduct) {
                throw new ProductException(ProductErrorConstant.PRODUCT_NOT_FOUND);
            }

            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new ProductException(ProductErrorConstant.INVALID_PRODUCT);
        }

        return Boolean.TRUE;
    }

    @Override
    public ProductModel getProductById(Long id) {
        ProductEntity product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorConstant.PRODUCT_NOT_FOUND));

        return productMapper.productEntityToProductModel(product);
    }

    @Override
    public List<ProductModel> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return productMapper.productEntityListToProductModelList(products);
    }

    @Override
    public List<ProductModel> getProductsByIds(List<Long> ids) {
        List<ProductEntity> products = productRepository.findAllById(ids);
        return productMapper.productEntityListToProductModelList(products);
    }
}
