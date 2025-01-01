package com.wif3006.igsservice.product.mappers;

import com.wif3006.igsservice.product.entities.ProductEntity;
import com.wif3006.igsservice.product.models.ProductModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductModel productEntityToProductModel(ProductEntity productEntity);

    List<ProductModel> productEntityListToProductModelList(List<ProductEntity> productEntityList);

    ProductEntity productModelToProductEntity(ProductModel productModel);

    List<ProductEntity> productEntityListToProductEntityList(List<ProductModel> productModelList);
}
