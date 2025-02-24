package org.example.dao;
import org.example.model.Product;

import java.sql.Connection;

public class ProductDAOImpl extends AbstractDAO<Product> {
    public ProductDAOImpl(Connection conn, String table){
        super(conn,table);
    }
}

