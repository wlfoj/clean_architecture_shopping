package com.cart.shopping.product.businessRules.useCases;

import com.cart.shopping.product.businessRules.entities.Product;
import com.cart.shopping.product.businessRules.exceptions.NegativePrice;
import com.cart.shopping.product.businessRules.exceptions.ProductNotFound;
import com.cart.shopping.product.businessRules.repositoryPorts.IProductRepository;

import java.util.List;
import java.util.Optional;

public class ProductService implements IProductService {
    private final IProductRepository productPort;

    public ProductService(IProductRepository productPort) {
        this.productPort = productPort;
    }

    @Override
    public Product addProduct(Product p) throws NegativePrice {
        if(!p.isValidPrice()){
            throw new NegativePrice();
        }
        return productPort.saveProduct(p);
    }

    @Override
    public List<Product> getAll() {
        return productPort.findAllProducts();
    }

    @Override
    public Product getById(long id) throws ProductNotFound {
        Product p = productPort.findByIdProduct(id);
        if(p == null){
            throw new ProductNotFound();
        }
        return p;
    }

    @Override
    public Product edit(long id, Product p) throws NegativePrice, ProductNotFound {
        // Se o dto não for válido
        if(!p.isValidPrice()){
            throw new NegativePrice();
        }
        Product editedProductEntity = productPort.updateProduct(id, p);
        // Se não tiver achado o produto
        if (editedProductEntity == null){
            throw new ProductNotFound();
        }
        return editedProductEntity;
    }

    @Override
    public void deleteById(long id) {
        productPort.deleteByIdProduct(id);
    }
}

