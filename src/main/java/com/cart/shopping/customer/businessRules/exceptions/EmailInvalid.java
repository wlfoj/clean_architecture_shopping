package com.cart.shopping.customer.businessRules.exceptions;

public class EmailInvalid extends Exception {

    private static final long serialVersionUID = 1L;

    public EmailInvalid() {
        super("Email informado é inválido, insira outro por favor");
    }
}
