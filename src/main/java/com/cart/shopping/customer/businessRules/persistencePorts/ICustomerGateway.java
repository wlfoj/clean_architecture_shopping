package com.cart.shopping.customer.businessRules.persistencePorts;

import com.cart.shopping.customer.businessRules.entities.Customer;

import java.util.List;

public interface ICustomerGateway {
    public List<Customer> getAllCustomers();
    public Customer getCustomerById(long id);
    public Customer addCustomer(Customer customer);
    public Customer updateCustomer(long id, Customer customer);
    public boolean deleteCustomer(long id);
}
