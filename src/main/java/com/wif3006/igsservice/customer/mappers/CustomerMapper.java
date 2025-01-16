package com.wif3006.igsservice.customer.mappers;

import com.wif3006.igsservice.customer.entities.CustomerEntity;
import com.wif3006.igsservice.customer.models.CustomerModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerModel customerEntityToCustomerModel(CustomerEntity customerEntity);

    List<CustomerModel> customerEntityListToCustomerModelList(List<CustomerEntity> customerEntities);

    CustomerEntity customerModelToCustomerEntity(CustomerModel customerModel);
}
