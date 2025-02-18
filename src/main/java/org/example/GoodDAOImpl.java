package org.example;
import java.sql.Connection;

public class GoodDAOImpl extends AbstractDAO<Good>{
    public GoodDAOImpl(Connection conn, String table){
        super(conn,table);
    }
}

