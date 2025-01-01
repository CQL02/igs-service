package com.wif3006.igsservice.customer.services;

import com.wif3006.igsservice.customer.models.CustomerModel;

import java.util.List;

public interface CustomerService {
    Boolean addCustomer(CustomerModel customerModel);
    Boolean updateCustomer(Long id, CustomerModel customerModel);
    Boolean deleteCustomer(Long id);
    CustomerModel getCustomerById(Long id);
    List<CustomerModel> getAllCustomers();
}
