package com.cart.shopping.customer.interfaceAdapters.repository;

import com.cart.shopping.customer.businessRules.exceptions.JDBCConnectionnFailed;

import java.sql.Connection;

public interface Conector {

    public Connection getConnectionn() throws JDBCConnectionnFailed;
}
