package com.cart.shopping.customer.frameworks;

import com.cart.shopping.customer.businessRules.exceptions.JDBCConnectionnFailed;
import com.cart.shopping.customer.businessRules.persistence.ICustomerRepository;
import com.cart.shopping.customer.businessRules.useCases.CustomerService;
import com.cart.shopping.customer.businessRules.useCases.ICustomerService;
import com.cart.shopping.customer.interfaceAdapters.controllers.CustomerController;
import com.cart.shopping.customer.interfaceAdapters.controllers.ICustomerController;
import com.cart.shopping.customer.interfaceAdapters.repository.Conector;
import com.cart.shopping.customer.interfaceAdapters.repository.CustomerRepositoryImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Classe para injeção de depêndencias no sistema para isolar a aplicação do framework */
@Configuration
public class BeanCustomerConfiguration {
    /** Bloco para o Bean dos Gateway (SE FOR NECESSÁRIO) **/
    @Bean
    ICustomerRepository customerRepository(Conector postgreSqlConn) throws JDBCConnectionnFailed {
        return new CustomerRepositoryImp(postgreSqlConn);
    }

    /** Bloco para o Bean dos Use Cases **/
    @Bean
    ICustomerService customerService(ICustomerRepository customerRepository){
        return new CustomerService(customerRepository);
    }

    /** Bloco para o Bean dos Controllers **/
    @Bean
    ICustomerController customerController(ICustomerService customerService){
        return new CustomerController(customerService);
    }

}

