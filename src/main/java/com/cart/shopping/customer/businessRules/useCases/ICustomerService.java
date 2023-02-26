package com.cart.shopping.customer.businessRules.useCases;

import com.cart.shopping.customer.businessRules.entities.Customer;
import com.cart.shopping.customer.businessRules.exceptions.CustomerNotFound;
import com.cart.shopping.customer.businessRules.exceptions.EmailInvalid;

import java.util.List;

public interface ICustomerService {
    public List<Customer> getAll();
    public Customer getById(long id) throws CustomerNotFound;
    public Customer addCustomers(Customer c) throws EmailInvalid;
    public Customer edit(long id, Customer c) throws EmailInvalid, CustomerNotFound;
    public void deleteById(long id) throws CustomerNotFound;
}
