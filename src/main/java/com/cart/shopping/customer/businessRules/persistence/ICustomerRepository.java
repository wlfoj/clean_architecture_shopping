package com.cart.shopping.customer.businessRules.persistence;

import com.cart.shopping.customer.businessRules.entities.Customer;

import java.util.List;

public interface ICustomerRepository {
    public List<Customer> getAllCustomers();
    public Customer getCustomerById(long id);
    public Customer addCustomer(Customer customer);
    public Customer updateCustomer(long id, Customer customer);
    public boolean deleteCustomer(long id);
}
