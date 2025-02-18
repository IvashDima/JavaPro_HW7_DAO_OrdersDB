package org.example.dao;
import org.example.model.Orders;

import java.sql.Connection;

public class OrderDAOImpl extends AbstractDAO<Orders> {
    public OrderDAOImpl(Connection conn, String table){
        super(conn,table);
    }
}
