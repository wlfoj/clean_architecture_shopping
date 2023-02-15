package com.cart.shopping.product.businessRules.exceptions;

public class ProductNotFound extends Exception{

    private static final long serialVersionUID = 1L;

    public ProductNotFound() {
        super("Não foi encontrado produto com o id especificado");
    }

}
