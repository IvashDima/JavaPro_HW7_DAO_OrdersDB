package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection())
        {
            try {
                try (Statement st = conn.createStatement()) {
//                    st.execute("DROP TABLE IF EXISTS Flats");
                    //st.execute("CREATE TABLE Flats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, district VARCHAR(100) NOT NULL, roomNum INT, square DOUBLE)");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ClientDAOImpl cdao = new ClientDAOImpl(conn, "Clients");
            cdao.createTable(Client.class);
            GoodDAOImpl gdao = new GoodDAOImpl(conn, "Goods");
            gdao.createTable(Good.class);
            OrderDAOImpl odao = new OrderDAOImpl(conn, "Orders");
            odao.createTable(Order.class);
            GoodsInOrderDAOImpl godao = new GoodsInOrderDAOImpl(conn, "Orders");
            godao.createTable(GoodsInOrder.class);

            Client c1 = new Client("Dmytro");
            cdao.add(c1);
            Client c2 = new Client("Petro");
            cdao.add(c2);
            Good g1 = new Good("TV");
            gdao.add(g1);
            Good g2 = new Good("Phone");
            gdao.add(g2);

//            Order f1 = new Order(c1, g2);
//            odao.add(f1);
//            Order f2 = new Order(c2, g1);
//            odao.add(f2);

            List<Order> list = odao.getAll(Order.class);
            for (Order order : list)
                System.out.println(order);

        }
    }
}