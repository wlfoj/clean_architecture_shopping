package com.cart.shopping.product.frameworks;

import com.cart.shopping.product.interfaceAdapters.controllers.IProductController;
import com.cart.shopping.product.interfaceAdapters.controllers.ProductController;
import com.cart.shopping.product.businessRules.persistencePorts.IProductGateway;
import com.cart.shopping.product.businessRules.useCases.IProductService;
import com.cart.shopping.product.businessRules.useCases.ProductService;
import com.cart.shopping.product.interfaceAdapters.gateway.IProductRepository;
import com.cart.shopping.product.interfaceAdapters.gateway.ProductGatewayImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Classe para injeção de depêndencias no sistema para isolar a aplicação do framework */
@Configuration
public class BeanConfiguration {
    /** Bloco para o Bean dos Gateway (SE FOR NECESSÁRIO) **/
    @Bean
    IProductGateway productGateway(IProductRepository productRepository){
        return new ProductGatewayImp(productRepository);
    }

    /** Bloco para o Bean dos Use Cases **/
    @Bean
    IProductService productService(IProductGateway productGateway){
        return new ProductService(productGateway);
    }

    /** Bloco para o Bean dos Controllers **/
    @Bean
    IProductController productController(IProductService productService){
        return new ProductController(productService);
    }

}
