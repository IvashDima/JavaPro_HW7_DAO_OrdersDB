package org.example.dao;
import org.example.model.GoodsInOrder;

import java.sql.Connection;

public class GoodsInOrderDAOImpl extends AbstractDAO<GoodsInOrder> {
    public GoodsInOrderDAOImpl(Connection conn, String table){
        super(conn,table);
    }
}
