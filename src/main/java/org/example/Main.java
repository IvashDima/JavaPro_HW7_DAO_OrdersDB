package org.example;

import org.example.dao.ClientDAOImpl;
import org.example.dao.GoodDAOImpl;
import org.example.model.Client;
import org.example.model.Good;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection())
        {
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Clients");
                    st.execute("DROP TABLE IF EXISTS Goods");
                    st.execute("DROP TABLE IF EXISTS Orders");
                    st.execute("DROP TABLE IF EXISTS GoodsInOrders");
                    //st.execute("CREATE TABLE Flats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, district VARCHAR(100) NOT NULL, roomNum INT, square DOUBLE)");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ClientDAOImpl cdao = new ClientDAOImpl(conn, "Clients");
            cdao.createTable(Client.class);
            GoodDAOImpl gdao = new GoodDAOImpl(conn, "Goods");
            gdao.createTable(Good.class);
//            OrderDAOImpl odao = new OrderDAOImpl(conn, "Orders");
//            odao.createTable(Orders.class);
//            GoodsInOrderDAOImpl godao = new GoodsInOrderDAOImpl(conn, "GoodsInOrders");
//            godao.createTable(GoodsInOrder.class);



            Client c1 = new Client("Dmytro");
            cdao.add(c1);
            Client c2 = new Client("Petro");
            cdao.add(c2);
            Good g1 = new Good("TV");
            gdao.add(g1);
            Good g2 = new Good("Phone");
            gdao.add(g2);
//
////            Order f1 = new Order(c1, g2);
////            odao.add(f1);
////            Order f2 = new Order(c2, g1);
////            odao.add(f2);
//
//            List<Orders> list = odao.getAll(Orders.class);
//            for (Orders order : list)
//                System.out.println(order);

        }
    }
}