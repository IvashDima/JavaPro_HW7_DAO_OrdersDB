package org.example;
import java.sql.Connection;

public class OrderDAOImpl extends AbstractDAO<Order>{
    public OrderDAOImpl(Connection conn, String table){
        super(conn,table);
    }
}
