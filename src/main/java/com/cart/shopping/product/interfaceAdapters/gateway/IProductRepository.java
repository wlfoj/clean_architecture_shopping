package com.cart.shopping.product.interfaceAdapters.gateway;

import com.cart.shopping.product.businessRules.entities.Product;

import java.util.List;

public interface IProductRepository {
    public List<Product> findAll();
    public Product findById(long id);
    public Product save(Product product);
    public Product update(long id, Product product);
    public void deleteById(long id);
}
