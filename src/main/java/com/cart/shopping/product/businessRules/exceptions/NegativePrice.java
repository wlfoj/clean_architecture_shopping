package com.cart.shopping.product.businessRules.exceptions;

public class NegativePrice extends Exception {

    private static final long serialVersionUID = 1L;

    public NegativePrice() {
        super("Preco informado negativo, insira um valor maior que 0");
    }
}
