package org.example;

import java.sql.Connection;

public class AbstractDAO <T> {
    private final Connection conn;
    private final String table;

    public AbstractDAO(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }


}
