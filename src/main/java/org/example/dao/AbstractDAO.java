package org.example.dao;

import org.example.model.Id;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO <T> {
    private final Connection conn;
    private final String table;

    public AbstractDAO(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }
    public void createTable(Class<T> cls){
        Field[] fields = cls.getDeclaredFields();
        Field id = getPrimaryKeyField(null, fields);

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ")
                .append(table)
                .append("(");
        sql.append(id.getName())
                .append(" ")
                .append(" INT AUTO_INCREMENT PRIMARY KEY,");

        for (Field f: fields){
            if(f != id){
                f.setAccessible(true);
                sql.append(f.getName()).append(" ");
                if(f.getType()==int.class){
                    sql.append("INT,");
                }else if (f.getType()==Class.class){
                    sql.append("VARCHAR(100),");
                }else if (f.getType()==String.class){
                    sql.append("VARCHAR(100),");
                }else if (f.getType()==double.class){
                    sql.append("DOUBLE,");
                }else
                    throw new RuntimeException("Wrong type");
            }
        }
        sql.deleteCharAt(sql.length()-1);
        sql.append(")");
        try {
            try (Statement st = conn.createStatement()){
                st.execute(sql.toString());
            }
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public void add(T t){
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = getPrimaryKeyField(t,fields);
            StringBuilder names = new StringBuilder();
            StringBuilder values = new StringBuilder();

            for (Field f: fields){
                if(f!=id){
                    f.setAccessible(true);
                    names.append(f.getName()).append(',');
                    values.append('"').append(f.get(t)).append("\",");
                }
            }
            names.deleteCharAt(names.length()-1);
            values.deleteCharAt(values.length()-1);

            String sql="INSERT INTO "+table+"("+names.toString()+
                    ") VALUES("+values.toString()+")";
            try (Statement st = conn.createStatement()){
                st.execute(sql);
            }
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public void update(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = getPrimaryKeyField(t, fields);

            StringBuilder sb = new StringBuilder();

            for (Field f : fields) {
                if (f != id) {
                    f.setAccessible(true);

                    sb.append(f.getName())
                            .append(" = ")
                            .append('"')
                            .append(f.get(t))
                            .append('"')
                            .append(',');
                }
            }

            sb.deleteCharAt(sb.length() - 1);

            // update t set name = "aaaa", age = "22" where id = 5
            String sql = "UPDATE " + table + " SET " + sb.toString() + " WHERE " +
                    id.getName() + " = \"" + id.get(t) + "\"";

            try (Statement st = conn.createStatement()) {
                st.execute(sql);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public List<T> getAll(Class<T> cls) {
        List<T> res = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM " + table)) {
            ResultSetMetaData md = rs.getMetaData();
            while (rs.next()) {
                T t = cls.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    String columnName = md.getColumnName(i);
                    try{
                        Field field = cls.getDeclaredField(columnName);
                        field.setAccessible(true);
                        Object value = rs.getObject(columnName);
                        if (value instanceof BigDecimal) {
                            value = ((BigDecimal) value).doubleValue();
                        }
                        field.set(t, value);
                    } catch (NoSuchFieldException e) {
                        System.err.println("No field found for column: " + columnName);
                    }
                }
                res.add(t);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return res;
    }
    public List<T> findIdByName(Class<T> cls, String name) {
        List<T> res = new ArrayList<>();
        try {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM "+table+" WHERE name=?")) {
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                try {
                    ResultSetMetaData md = rs.getMetaData();
                    while (rs.next()) {
                        T t = cls.getDeclaredConstructor().newInstance();
                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);
                            Field field = cls.getDeclaredField(columnName);
                            field.setAccessible(true);
                            field.set(t, rs.getObject(columnName));
                        }
                        res.add(t);
                    }
                } finally {
                    rs.close();
                }
            }
            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    private Field getPrimaryKeyField(T t, Field[] fields){
        Field result = null;
        for(Field f: fields){
            if(f.isAnnotationPresent(Id.class)){
                result=f;
                result.setAccessible(true);
                break;
            }
        }
        if(result==null){
            throw new RuntimeException("No If field found");
        }
        return result;
    }

}
