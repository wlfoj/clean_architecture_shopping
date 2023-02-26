package com.cart.shopping.customer.frameworks;

import com.cart.shopping.customer.businessRules.exceptions.JDBCConnectionnFailed;
import com.cart.shopping.customer.businessRules.persistencePorts.ICustomerGateway;
import com.cart.shopping.customer.businessRules.useCases.CustomerService;
import com.cart.shopping.customer.businessRules.useCases.ICustomerService;
import com.cart.shopping.customer.frameworks.dataBase.PostgreSqlConn;
import com.cart.shopping.customer.interfaceAdapters.controllers.CustomerController;
import com.cart.shopping.customer.interfaceAdapters.controllers.ICustomerController;
import com.cart.shopping.customer.interfaceAdapters.gateway.CustomerGatewayImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;

/** Classe para injeção de depêndencias no sistema para isolar a aplicação do framework */
@Configuration
public class BeanCustomerConfiguration {
    /** Bloco para o Bean dos Gateway (SE FOR NECESSÁRIO) **/
    @Bean
    ICustomerGateway customerGateway(PostgreSqlConn postgreSqlConn) throws JDBCConnectionnFailed {
        return new CustomerGatewayImp(postgreSqlConn);
    }

    /** Bloco para o Bean dos Use Cases **/
    @Bean
    ICustomerService customerService(ICustomerGateway customerGateway){
        return new CustomerService(customerGateway);
    }

    /** Bloco para o Bean dos Controllers **/
    @Bean
    ICustomerController customerController(ICustomerService customerService){
        return new CustomerController(customerService);
    }

}

