package com.cart.shopping.product.businessRules.repositoryPorts;

import com.cart.shopping.product.businessRules.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    List<Product> findAll();
    Optional<Product> findById(long id);
    Product save(Product c);
    void deleteById(long id);
}
