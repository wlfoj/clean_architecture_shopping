package com.cart.shopping.customer.frameworks.dataBase;

import com.cart.shopping.customer.businessRules.exceptions.JDBCConnectionnFailed;
import com.cart.shopping.customer.interfaceAdapters.repository.Conector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Component
public class PostgreSqlConn implements Conector{
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public Connection getConnectionn() throws JDBCConnectionnFailed {
        Connection conn;
        try {
            // Criando a conexão
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (SQLException e) {
            throw new JDBCConnectionnFailed();
        }
    }
}
