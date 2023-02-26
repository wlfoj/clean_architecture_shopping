package com.cart.shopping.customer.businessRules.useCases;

import com.cart.shopping.customer.businessRules.entities.Customer;
import com.cart.shopping.customer.businessRules.exceptions.CustomerNotFound;
import com.cart.shopping.customer.businessRules.exceptions.EmailInvalid;
import com.cart.shopping.customer.businessRules.persistencePorts.ICustomerGateway;

import java.util.List;

public class CustomerService implements ICustomerService{
    private final ICustomerGateway customerPort;

    public CustomerService(ICustomerGateway customerPort) {
        this.customerPort = customerPort;
    }

    @Override
    public List<Customer> getAll() {
        return customerPort.getAllCustomers();
    }

    @Override
    public Customer getById(long id) throws CustomerNotFound {
        Customer c = customerPort.getCustomerById(id);
        if (c == null){
            throw new CustomerNotFound();
        }
        return c;
    }


    @Override
    public Customer addCustomers(Customer c) throws EmailInvalid {
        if (!c.isEmailValid()){
            throw new EmailInvalid();
        }
        Customer _c = customerPort.addCustomer(c);
        return _c;
    }

    @Override
    public Customer edit(long id, Customer c) throws EmailInvalid, CustomerNotFound {
        if (!c.isEmailValid()){
            throw new EmailInvalid();
        }
        Customer _c = customerPort.updateCustomer(id, c);
        // Se não tiver encontrado
        if (_c == null){
            throw new CustomerNotFound();
        }
        return _c;
    }

    @Override
    public void deleteById(long id) throws CustomerNotFound {
        boolean aux;
        aux = customerPort.deleteCustomer(id);
        if (aux == false){
            throw new CustomerNotFound();
        }
    }
}
