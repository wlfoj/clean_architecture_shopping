package com.cart.shopping.product.businessRules.useCases;

import com.cart.shopping.product.businessRules.entities.Product;
import com.cart.shopping.product.businessRules.exceptions.NegativePrice;
import com.cart.shopping.product.businessRules.exceptions.ProductNotFound;

import java.util.List;

public interface IProductService {
    public Product addProduct(Product p) throws NegativePrice;
    public List<Product> getAll();
    public Product getById(long id) throws ProductNotFound;
    public Product edit(long id, Product p) throws NegativePrice, ProductNotFound;
    public void deleteById(long id);
}
