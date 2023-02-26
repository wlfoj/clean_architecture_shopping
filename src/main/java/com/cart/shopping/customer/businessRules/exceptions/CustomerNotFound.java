package com.cart.shopping.customer.businessRules.exceptions;

public class CustomerNotFound extends Exception{

    private static final long serialVersionUID = 1L;

    public CustomerNotFound() {
        super("Não foi encontrado cliente com o id especificado");
    }

}
