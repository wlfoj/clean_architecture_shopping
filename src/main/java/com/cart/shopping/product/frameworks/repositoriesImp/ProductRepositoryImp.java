package com.cart.shopping.product.frameworks.repositoriesImp;

import com.cart.shopping.product.businessRules.entities.Product;
import com.cart.shopping.product.businessRules.repositoryPorts.IProductRepository;
import com.cart.shopping.product.frameworks.databaseJPA.ProductRepositoryJPA;
import com.cart.shopping.product.frameworks.entitiesJPA.ProductEntityJPA;
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
        return ProductEntityJPA.toProductList(this.productRepositoryJPA.findAll());
    }

    @Override
    public Product findById(long id) {
        // Se estiver dando erro vai ser aqui quando não houver nada
        return this.productRepositoryJPA.findById(id).get().toProduct();
    }

    @Override
    public Product save(Product p) {
        ProductEntityJPA prod = new ProductEntityJPA(p);
        return this.productRepositoryJPA.save(prod).toProduct();
    }

    @Override
    public void deleteById(long id) {
        this.productRepositoryJPA.deleteById(id);
    }
}
