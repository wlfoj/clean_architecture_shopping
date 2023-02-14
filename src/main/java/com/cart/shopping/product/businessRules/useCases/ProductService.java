package com.cart.shopping.product.businessRules.useCases;

import com.cart.shopping.product.businessRules.entities.Product;
import com.cart.shopping.product.businessRules.exceptions.NegativePrice;
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
        return productPort.save(p);
    }

    @Override
    public List<Product> getAll() {
        return productPort.findAll();
    }

    @Override
    public Product getById(long id) {
        Optional<Product> p = productPort.findById(id);
        if(p.isPresent()){
            return p.get();
        }
        return null;
    }

    @Override
    public Product edit(long id, Product p) throws NegativePrice {
        if(!p.isValidPrice()){
            throw new NegativePrice();
        }
        Product editedProductEntity = null;
        // Verifica se existe alguém com o id
        Optional<Product> _product= productPort.findById(id);
        // Se tiver preenche
        if (_product.isPresent()) {
            editedProductEntity = _product.get();
            editedProductEntity.setName(p.getName());
            editedProductEntity.setPrice(p.getPrice());
            editedProductEntity = productPort.save(editedProductEntity);
        }
        return editedProductEntity;
    }

    @Override
    public void deleteById(long id) {
        productPort.deleteById(id);
    }
}

