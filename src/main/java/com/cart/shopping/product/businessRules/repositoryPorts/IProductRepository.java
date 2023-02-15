package com.cart.shopping.product.businessRules.repositoryPorts;

import com.cart.shopping.product.businessRules.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    List<Product> findAllProducts();
    Optional<Product> findByIdProduct(long id);
    Product saveProduct(Product c);
    Product updateProduct(long id, Product c);
    void deleteByIdProduct(long id);
}
