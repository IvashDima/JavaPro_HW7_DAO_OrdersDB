package org.example.dao;
import org.example.model.Client;

import java.sql.Connection;

public class ClientDAOImpl extends AbstractDAO<Client> {
    public ClientDAOImpl(Connection conn, String table){
        super(conn,table);
    }
}
