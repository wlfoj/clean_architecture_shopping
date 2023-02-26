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
    //@Value("${spring.datasource.url}")
    private static String url = "jdbc:postgresql://localhost:5432/shoppingcart";
    //@Value("${spring.datasource.username}")
    private static String username = "root";
    //@Value("${spring.datasource.password}")
    private static String password = "1234";

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
