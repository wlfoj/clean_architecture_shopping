package com.cart.shopping.product.frameworks.repositoryImp;

import com.cart.shopping.product.businessRules.entities.Product;
import com.cart.shopping.product.frameworks.databaseJPA.ProductRepositoryJPA;
import com.cart.shopping.product.frameworks.entitiesJPA.ProductEntityJPA;
import com.cart.shopping.product.interfaceAdapters.gateway.IProductRepository;
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
        List<ProductEntityJPA> list = this.productRepositoryJPA.findAll();
        return ProductMapper.toProductList(list);
    }

    @Override
    public Product findById(long id) {
        Optional<ProductEntityJPA> prod = this.productRepositoryJPA.findById(id);
        if (prod.isPresent()){
            return ProductMapper.toProduct(prod.get());
        }
        return null;
    }

    @Override
    public Product save(Product product) {
        ProductEntityJPA p = ProductMapper.toProductJPA(product);
        return ProductMapper.toProduct(this.productRepositoryJPA.save(p));
    }

    @Override
    public Product update(long id, Product product) {
        ProductEntityJPA editedProductEntity = null;
        // Verifica se existe alguém com o id
        Optional<ProductEntityJPA> _product= this.productRepositoryJPA.findById(id);
        // Se tiver preenche
        if (_product.isPresent()) {
            editedProductEntity = _product.get();
            editedProductEntity.setName(product.getName());
            editedProductEntity.setPrice(product.getPrice());
            editedProductEntity = this.productRepositoryJPA.save(editedProductEntity);
            return ProductMapper.toProduct(editedProductEntity);
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        this.productRepositoryJPA.deleteById(id);
    }
}
