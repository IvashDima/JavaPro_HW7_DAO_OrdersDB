package org.example.dao;
import org.example.model.ProductsInOrder;

import java.sql.Connection;

public class ProductsInOrderDAOImpl extends AbstractDAO<ProductsInOrder> {
    public ProductsInOrderDAOImpl(Connection conn, String table){
        super(conn,table);
    }
}
