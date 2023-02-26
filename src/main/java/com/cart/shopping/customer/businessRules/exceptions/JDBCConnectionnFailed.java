package com.cart.shopping.customer.businessRules.exceptions;

public class JDBCConnectionnFailed extends Exception{

    private static final long serialVersionUID = 1L;

    public JDBCConnectionnFailed() {
        super("A conexão com o banco falhou");
    }

}