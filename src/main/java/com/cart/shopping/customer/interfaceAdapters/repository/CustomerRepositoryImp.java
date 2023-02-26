package com.cart.shopping.customer.interfaceAdapters.repository;

import com.cart.shopping.customer.businessRules.entities.Customer;
import com.cart.shopping.customer.businessRules.exceptions.JDBCConnectionnFailed;
import com.cart.shopping.customer.businessRules.persistence.ICustomerRepository;
import com.cart.shopping.customer.interfaceAdapters.presenters.CustomerPresenter;

import java.sql.*;
import java.util.List;

public class CustomerRepositoryImp implements ICustomerRepository {

    private final Connection connection;

//    public CustomerGatewayImp(Connection connection) {
//        this.connection = connection;
//    }
    public CustomerRepositoryImp(Conector conn) throws JDBCConnectionnFailed {
        this.connection = conn.getConnectionn();
    }


    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customers";
        List<Customer> customerList = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            customerList = CustomerPresenter.mapRowsCustomer(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }


    @Override
    public Customer getCustomerById(long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return CustomerPresenter.mapRowCustomer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Customer addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, email, address, phone) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());

//            int num_rows = statement.executeUpdate();
            // Se tiver alterado as linhas
//            if (num_rows == 1){
//                return true;
//            }
            statement.executeUpdate();
            ResultSet res = statement.getGeneratedKeys();
            if(res.next()){
                customer.setId(res.getLong(1));
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Se não tiver gerado id
        return null;
    }


    @Override
    public Customer updateCustomer(long id, Customer customer) {
        String sql = "UPDATE customers SET name = ?, email = ?, address = ?, phone = ? WHERE id = ?";
        int num_rows;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());
            statement.setLong(5, id);

            num_rows = statement.executeUpdate();
            // Se tiver alterado as linhas
            if (num_rows == 1){
                customer.setId(id);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Se não tiver alterado as linhas
        return null;
    }


    @Override
    public boolean deleteCustomer(long id){
        String sql = "DELETE FROM customers WHERE id = ?";
        int num_rows;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            num_rows = statement.executeUpdate();
            // Se tiver alterado as linhas
            if (num_rows == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Se não tiver alterado as linhas
        return false;
    }

}
