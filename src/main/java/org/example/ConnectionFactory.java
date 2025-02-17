package org.example;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
    private static boolean inited;
    public static Connection getConnection() throws SQLException{
        initialize();
        return dataSource.getConnection();
    }
    private static void initialize(){
        if(inited) return;
        Properties properties = new Properties();
        try {
            properties.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties"));
            dataSource.setDriverClass(properties.getProperty("db.driver"));
            dataSource.setJdbcUrl(properties.getProperty("db.url"));
            dataSource.setUser(properties.getProperty("db.user"));
            dataSource.setPassword(properties.getProperty("db.password"));
            inited = true;
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}