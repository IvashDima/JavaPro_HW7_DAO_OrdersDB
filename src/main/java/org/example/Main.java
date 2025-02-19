package org.example;

import org.example.dao.ClientDAOImpl;
import org.example.dao.GoodDAOImpl;
import org.example.dao.GoodsInOrderDAOImpl;
import org.example.dao.OrderDAOImpl;
import org.example.model.Client;
import org.example.model.Good;
import org.example.model.GoodsInOrder;
import org.example.model.Orders;

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
            OrderDAOImpl odao = new OrderDAOImpl(conn, "Orders");
            odao.createTable(Orders.class);
            GoodsInOrderDAOImpl godao = new GoodsInOrderDAOImpl(conn, "GoodsInOrders");
            godao.createTable(GoodsInOrder.class);

            Client c1 = new Client("Dmytro");
            cdao.add(c1);
            Client c2 = new Client("Petro");
            cdao.add(c2);
            List<Client> clientList = cdao.getAll(Client.class);
            System.out.println("Clients were added:");
            for (Client client : clientList) {
                System.out.print(client.getId()+"\t");
                System.out.println(client.getName());
            }

            Good g1 = new Good("TV");
            gdao.add(g1);
            Good g2 = new Good("Phone");
            gdao.add(g2);
            List<Good> goodList = gdao.getAll(Good.class);
            System.out.println("Goods were added:");
            for (Good good : goodList){
                System.out.print(good.getId()+"\t");
                System.out.println(good.getName());
            }
            List<Client> filteredClientsList = cdao.findIdByName(Client.class,
                    "Dmytro");
            System.out.print("READ client throw DAO: ");
            for (Client dmytro : filteredClientsList){
                Orders o1 = new Orders(dmytro.getId());
                odao.add(o1);
                System.out.println(o1.getClient());
            }

            System.out.println("READ client throw getter: "+ c2.getId());
            Orders o2 = new Orders(c2.getId());
            odao.add(o2);
            List<Orders> orderList = odao.getAll(Orders.class);
            System.out.println("Orders were created:");
            for (Orders order : orderList)
                System.out.println(order.getClient());
//
//            GoodsInOrder go1 = new GoodsInOrder(o1.getId(), g1.getId());
//            godao.add(go1);
            GoodsInOrder go2 = new GoodsInOrder(o2.getId(), g2.getId());
            godao.add(go2);
            List<GoodsInOrder> goodsInOrderList = godao.getAll(GoodsInOrder.class);
            System.out.println("GoodsInOrder were created:");
            for (GoodsInOrder goodsInOrder : goodsInOrderList)
                System.out.println(goodsInOrder.getOrder()+" "+goodsInOrder.getGood());

        }
    }
}