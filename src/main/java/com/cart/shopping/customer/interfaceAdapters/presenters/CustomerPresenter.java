package com.cart.shopping.customer.interfaceAdapters.presenters;

import com.cart.shopping.customer.businessRules.entities.Customer;
import com.cart.shopping.customer.interfaceAdapters.dtos.request.CustomerReqDto;
import com.cart.shopping.customer.interfaceAdapters.dtos.response.CustomerResDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerPresenter {

    // Muda de row para entity (Uma Linha)
    public static Customer mapRowCustomer(ResultSet resultSet) throws SQLException {

        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setName(resultSet.getString("name"));
        customer.setEmail(resultSet.getString("email"));
        customer.setAddress(resultSet.getString("address"));
        customer.setPhone(resultSet.getString("phone"));
        return customer;
    }


    // Muda de rows para list<Customer> (várias linhas)
    public static List<Customer> mapRowsCustomer(ResultSet resultSet) throws SQLException{
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = mapRowCustomer(resultSet);
            customerList.add(customer);
        }
        return customerList;
    }


    // Muda de entity para dto response
    public static CustomerResDto customerToDtoRes(Customer customer){
        CustomerResDto c = new CustomerResDto();
        c.email = customer.getEmail();
        c.id = customer.getId();
        c.name = customer.getName();
        c.phone = customer.getPhone();
        return c;
    }

    // Muda de list entity para dto response
    public static List<CustomerResDto> listCutomerToListDto(List<Customer> listCustomers){
        List<CustomerResDto> responseList = new ArrayList<>();
        for (Customer _c: listCustomers) {
            responseList.add(customerToDtoRes(_c));
        }
        return responseList;
    }


    // Muda de dto request para entity
    public static Customer dtoReqToCustomer (CustomerReqDto cReq){
        Customer c = new Customer();
        c.setPhone(cReq.phone);
        c.setAddress(cReq.address);
        c.setEmail(cReq.email);
        c.setName(cReq.name);
        return c;
    }


    /** Converte o dtoResponse para uma String para enviar ao RabbitMQ
     *
     * @param cRes: dto customer response
     * @return String json
     */
    public static String dtoResToStringJson(CustomerResDto cRes){
        String res = "";
        res = res + "{\"email\":\""+cRes.email+"\",";
        res = res + "\"name\":\""+cRes.name +"\",";
        res = res + "\"id\":"+cRes.id +"}";
        return res;
    }
}
