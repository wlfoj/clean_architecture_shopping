package com.cart.shopping.product.interfaceAdapters.gateway;

import com.cart.shopping.product.businessRules.entities.Product;
import com.cart.shopping.product.businessRules.persistencePorts.IProductGateway;

import java.util.List;

/** Devido a utilização de um ORM JPA, se fez necessário que o gateway servisse apenas passando as responsabilidades
 *  para o repositoryImplementation. Com isso, não violamos a regra das camadas e suas depedências */
public class ProductGatewayImp implements IProductGateway {
    private final IProductRepository productRepositoryImp;

    public ProductGatewayImp(IProductRepository productRepositoryJPA) {
        this.productRepositoryImp = productRepositoryJPA;
    }

    @Override
    public List<Product> findAllProducts() {
        return this.productRepositoryImp.findAll();
    }

    @Override
    public Product findByIdProduct(long id) {
        // Se estiver dando erro vai ser aqui quando não houver nada
        return this.productRepositoryImp.findById(id);
    }

    @Override
    public Product saveProduct(Product p) {
        return this.productRepositoryImp.save(p);
    }

    @Override
    public Product updateProduct(long id, Product p) {
        return this.productRepositoryImp.update(id, p);
    }


    @Override
    public void deleteByIdProduct(long id) {
        this.productRepositoryImp.deleteById(id);
    }
}