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
    public List<Product> findAllProducts() {
        return ProductEntityJPA.toProductList(this.productRepositoryJPA.findAll());
    }

    @Override
    public Product findByIdProduct(long id) {
        // Se estiver dando erro vai ser aqui quando não houver nada
        Optional<ProductEntityJPA> product = this.productRepositoryJPA.findById(id);
        if(product.isPresent()){
            return product.get().toProduct();
        }
        else {
            return null;
        }
    }

    @Override
    public Product saveProduct(Product p) {
        ProductEntityJPA prod = new ProductEntityJPA(p);
        return this.productRepositoryJPA.save(prod).toProduct();
    }

    @Override
    public Product updateProduct(long id, Product p) {
        ProductEntityJPA editedProductEntity = null;
        // Verifica se existe alguém com o id
        Optional<ProductEntityJPA> _product= this.productRepositoryJPA.findById(id);
        // Se tiver preenche
        if (_product.isPresent()) {
            editedProductEntity = _product.get();
            editedProductEntity.setName(p.getName());
            editedProductEntity.setPrice(p.getPrice());
            editedProductEntity = this.productRepositoryJPA.save(editedProductEntity);
        }

        // Para evitar erro quando editedProductEntity ainda for null
        if (editedProductEntity == null){
            return null;
        }
        return editedProductEntity.toProduct();
    }


    @Override
    public void deleteByIdProduct(long id) {
        this.productRepositoryJPA.deleteById(id);
    }
}
