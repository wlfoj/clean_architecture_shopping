package com.cart.shopping.customer.interfaceAdapters.controllers;

import com.cart.shopping.customer.businessRules.exceptions.CustomerNotFound;
import com.cart.shopping.customer.businessRules.exceptions.EmailInvalid;
import com.cart.shopping.customer.interfaceAdapters.dtos.request.CustomerReqDto;
import com.cart.shopping.customer.interfaceAdapters.dtos.response.CustomerResDto;

import java.util.List;

public interface ICustomerController {
    public List<CustomerResDto> getAll();
    public CustomerResDto getById(long id) throws CustomerNotFound;
    public CustomerResDto createCustomer(CustomerReqDto c) throws EmailInvalid;
    public CustomerResDto updateCustomer(long id, CustomerReqDto c) throws EmailInvalid, CustomerNotFound;
    public void deleteCustomer(long id) throws CustomerNotFound;
}
