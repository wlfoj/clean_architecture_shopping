package com.cart.shopping.customer.interfaceAdapters.controllers;

import com.cart.shopping.customer.businessRules.entities.Customer;
import com.cart.shopping.customer.businessRules.exceptions.CustomerNotFound;
import com.cart.shopping.customer.businessRules.exceptions.EmailInvalid;
import com.cart.shopping.customer.businessRules.useCases.ICustomerService;
import com.cart.shopping.customer.interfaceAdapters.dtos.request.CustomerReqDto;
import com.cart.shopping.customer.interfaceAdapters.dtos.response.CustomerResDto;
import com.cart.shopping.customer.interfaceAdapters.presenters.CustomerPresenter;

import java.util.List;

public class CustomerController implements ICustomerController{

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService){
        this.customerService = customerService;
    }

    @Override
    public List<CustomerResDto> getAll() {
        return CustomerPresenter.listCutomerToListDto(customerService.getAll());
    }

    @Override
    public CustomerResDto getById(long id) throws CustomerNotFound {
        Customer c = customerService.getById(id);
        return CustomerPresenter.customerToDtoRes(c);
    }

    @Override
    public CustomerResDto createCustomer(CustomerReqDto c) throws EmailInvalid {
        Customer _c = CustomerPresenter.dtoReqToCustomer(c);
        return CustomerPresenter.customerToDtoRes(customerService.addCustomers(_c));
    }

    @Override
    public CustomerResDto updateCustomer(long id, CustomerReqDto c) throws EmailInvalid, CustomerNotFound {
        Customer _c = CustomerPresenter.dtoReqToCustomer(c);
        return CustomerPresenter.customerToDtoRes(customerService.edit(id, _c));
    }

    @Override
    public void deleteCustomer(long id) throws CustomerNotFound {
        customerService.deleteById(id);
    }
}
