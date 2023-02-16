package com.cart.shopping.product.businessRules.persistencePorts;

import com.cart.shopping.product.businessRules.entities.Product;

import java.util.List;

public interface IProductGateway {
    List<Product> findAllProducts();
    Product findByIdProduct(long id);
    Product saveProduct(Product c);
    Product updateProduct(long id, Product c);
    void deleteByIdProduct(long id);
}
