package com.cart.shopping.product.frameworks;

import com.cart.shopping.product.adapters.controllers.IProductController;
import com.cart.shopping.product.adapters.controllers.ProductController;
import com.cart.shopping.product.businessRules.repositoryPorts.IProductRepository;
import com.cart.shopping.product.businessRules.useCases.IProductService;
import com.cart.shopping.product.businessRules.useCases.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Classe para injeção de depêndencias no sistema para isolar as regras do framework
 */
@Configuration
public class BeanConfiguration {
    /** Bloco para o Bean dos Use Cases **/
    @Bean
    IProductService productService(IProductRepository productRepositoryPort){
        return new ProductService(productRepositoryPort);
    }

    /** Bloco para o Bean dos Controllers **/
    @Bean
    IProductController productController(IProductService productService){
        return new ProductController(productService);
    }

    /** Bloco para o Bean dos Repository (SE FOR NECESSÁRIO) **/
}
