package org.example;
import java.sql.Connection;

public class GoodsInOrderDAOImpl extends AbstractDAO<GoodsInOrder>{
    public GoodsInOrderDAOImpl(Connection conn, String table){
        super(conn,table);
    }
}
