package com.cart.shopping.product.frameworks.repositoriesImp;

import com.cart.shopping.product.businessRules.entities.Product;
import com.cart.shopping.product.businessRules.repositoryPorts.IProductRepository;
import com.cart.shopping.product.frameworks.databaseJPA.ProductRepositoryJPA;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryImp implements IProductRepository {
    private final ProductRepositoryJPA productRepositoryJPA;

    public ProductRepositoryImp(ProductRepositoryJPA productRepositoryJPA) {
        this.productRepositoryJPA = productRepositoryJPA;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepositoryJPA.findAll();
    }

    @Override
    public Optional<Product> findById(long id) {
        return this.productRepositoryJPA.findById(id);
    }

    @Override
    public Product save(Product c) {
        return this.productRepositoryJPA.save(c);
    }

    @Override
    public void deleteById(long id) {
        this.productRepositoryJPA.deleteById(id);
    }
}
