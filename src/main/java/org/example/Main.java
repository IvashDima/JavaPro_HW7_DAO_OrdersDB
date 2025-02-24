package org.example;

import org.example.dao.ClientDAOImpl;
import org.example.dao.ProductDAOImpl;
import org.example.dao.ProductsInOrderDAOImpl;
import org.example.dao.OrderDAOImpl;
import org.example.model.Client;
import org.example.model.Product;
import org.example.model.ProductsInOrder;
import org.example.model.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
//    public static ClientDAOImpl, ProductDAOImpl, OrderDAOImpl, ProductsInOrderDAOImpl tableCreation(Connection conn){
//        ClientDAOImpl cdao = new ClientDAOImpl(conn, "Clients");
//        cdao.createTable(Client.class);
//        ProductDAOImpl gdao = new ProductDAOImpl(conn, "Products");
//        gdao.createTable(Product.class);
//        OrderDAOImpl odao = new OrderDAOImpl(conn, "Orders");
//        odao.createTable(Orders.class);
//        ProductsInOrderDAOImpl godao = new ProductsInOrderDAOImpl(conn, "ProductsInOrders");
//        godao.createTable(ProductsInOrder.class);
//        return cdao, gdao, odao, godao;
//    }
//    public static void  addData(ClientDAOImpl cdao, ProductDAOImpl gdao, OrderDAOImpl odao, ProductsInOrderDAOImpl godao){
//        Client c1 = new Client("Dmytro");
//        cdao.add(c1);
//        Client c2 = new Client("Petro");
//        cdao.add(c2);
//        System.out.println("Clients were added:"+cdao.getAll(Client.class));
//    }
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection())
        {
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Clients");
                    st.execute("DROP TABLE IF EXISTS Products");
                    st.execute("DROP TABLE IF EXISTS Orders");
                    st.execute("DROP TABLE IF EXISTS ProductsInOrders");
                    //st.execute("CREATE TABLE Flats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, district VARCHAR(100) NOT NULL, roomNum INT, square DOUBLE)");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ClientDAOImpl cdao = new ClientDAOImpl(conn, "Clients");
            cdao.createTable(Client.class);
            ProductDAOImpl gdao = new ProductDAOImpl(conn, "Products");
            gdao.createTable(Product.class);
            OrderDAOImpl odao = new OrderDAOImpl(conn, "Orders");
            odao.createTable(Orders.class);
            ProductsInOrderDAOImpl godao = new ProductsInOrderDAOImpl(conn, "ProductsInOrders");
            godao.createTable(ProductsInOrder.class);
//            tableCreation(conn);
//            addData(cdao, gdao, odao, godao);

            Client c1 = new Client("Dmytro");
            cdao.add(c1);
            Client c2 = new Client("Petro");
            cdao.add(c2);
            System.out.println("Clients were added:"+cdao.getAll(Client.class));
//            List<Client> clientList = cdao.getAll(Client.class);
//            for (Client client : clientList) {
//                System.out.print(client.getId()+"\t");
//                System.out.println(client.getName());
//            }

            Product g1 = new Product("TV", 5000);
            gdao.add(g1);
            Product g2 = new Product("Phone", 1000);
            gdao.add(g2);
//            List<Product> productList = gdao.getAll(Product.class);
            System.out.println("Products were added:"+gdao.getAll(Product.class));
//            for (Product good : productList){
//                System.out.print(good.getId()+"\t");
//                System.out.println(good.getName());
//            }
            List<Client> filteredClientsList = cdao.findIdByName(Client.class,
                    "Dmytro");
            System.out.print("READ client throw DAO: ");
            for (Client dmytro : filteredClientsList){
                Orders o1 = new Orders(dmytro.getId());
                odao.add(o1);
                System.out.println(o1.getClient());
            }

            System.out.println("c: "+ c2.getId());
            Orders o2 = new Orders(c2.getId());
            odao.add(o2);
            List<Orders> orderList = odao.getAll(Orders.class);
            System.out.println("Orders were created:");
            for (Orders order : orderList)
                System.out.println(order.getClient());
//
//            GoodsInOrder go1 = new GoodsInOrder(o1.getId(), g1.getId());
//            godao.add(go1);
            ProductsInOrder go2 = new ProductsInOrder(o2.getId(), g2.getId());
            godao.add(go2);
            List<ProductsInOrder> goodsInOrderList = godao.getAll(ProductsInOrder.class);
            System.out.println("GoodsInOrder were created:");
            for (ProductsInOrder goodsInOrder : goodsInOrderList)
                System.out.println(goodsInOrder.getOrder()+" "+goodsInOrder.getGood());

        }
    }
}