package com.wif3006.igsservice.customer.services;

import com.wif3006.igsservice.customer.entities.CustomerEntity;
import com.wif3006.igsservice.customer.mappers.CustomerMapper;
import com.wif3006.igsservice.customer.models.CustomerModel;
import com.wif3006.igsservice.customer.repositories.CustomerRepository;
import com.wif3006.igsservice.shared.constant.CustomerErrorConstant;
import com.wif3006.igsservice.shared.exception.CustomerException;
import com.wif3006.igsservice.shared.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Boolean addCustomer(CustomerModel customerModel) {
        CustomerEntity customerEntity = customerMapper.customerModelToCustomerEntity(customerModel);
        try{
            customerRepository.save(customerEntity);
        } catch (Exception e){
            throw new CustomerException(CustomerErrorConstant.INVALID_CUSTOMER);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean updateCustomer(Long id, CustomerModel customerModel) {
        CustomerEntity updatedEntity = customerMapper.customerModelToCustomerEntity(customerModel);
        updatedEntity.setId(id);

        try{
            customerRepository.save(updatedEntity);
        } catch (Exception e){
            throw new CustomerException(CustomerErrorConstant.INVALID_CUSTOMER);
        }

        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteCustomer(Long id) {
        try {
            boolean validCustomer = customerRepository.existsById(id);
            if (!validCustomer) {
                throw new CustomerException(CustomerErrorConstant.CUSTOMER_NOT_FOUND);
            }
            customerRepository.deleteById(id);
        } catch (Exception e){
            throw new CustomerException(CustomerErrorConstant.INVALID_CUSTOMER);
        }

        return Boolean.TRUE;
    }

    @Override
    public CustomerModel getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerException(CustomerErrorConstant.CUSTOMER_NOT_FOUND));
        return customerMapper.customerEntityToCustomerModel(customerEntity);
    }

    @Override
    public List<CustomerModel> getAllCustomers() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerMapper.customerEntityListToCustomerModelList(customerEntities);
    }
}
