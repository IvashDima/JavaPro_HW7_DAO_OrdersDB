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
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection())
        {
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Clients");
                    st.execute("DROP TABLE IF EXISTS Products");
                    st.execute("DROP TABLE IF EXISTS Orders");
                    st.execute("DROP TABLE IF EXISTS ProductsInOrders");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ClientDAOImpl cdao = new ClientDAOImpl(conn, "Clients");
            cdao.createTable(Client.class);
            ProductDAOImpl pdao = new ProductDAOImpl(conn, "Products");
            pdao.createTable(Product.class);
            OrderDAOImpl odao = new OrderDAOImpl(conn, "Orders");
            odao.createTable(Orders.class);
            ProductsInOrderDAOImpl podao = new ProductsInOrderDAOImpl(conn, "ProductsInOrders");
            podao.createTable(ProductsInOrder.class);

            Client c1 = new Client("Ivan Ivanov");
            Client c2 = new Client("Petro Petrov");
            cdao.add(c1);
            cdao.add(c2);
            System.out.println("Clients were added: "+cdao.getAll(Client.class));

            Product p1 = new Product("TV", 5000.00);
            Product p2 = new Product("Phone", 1000.00);
            Product p3= new Product("Silicon case", 10.50);
            pdao.add(p1);
            pdao.add(p2);
            pdao.add(p3);
            System.out.println("Products were added: "+pdao.getAll(Product.class));

            Orders o1 = new Orders(1);
            Orders o2 = new Orders(2);
            odao.add(o1);
            odao.add(o2);
            System.out.println("Orders were added: "+odao.getAll(Orders.class));

            ProductsInOrder po1 = new ProductsInOrder(o1.getId(), p1.getId(), 1);
            ProductsInOrder po2 = new ProductsInOrder(o2.getId(), p2.getId(),1);
            ProductsInOrder po3 = new ProductsInOrder(o2.getId(), p3.getId(),2);
            podao.add(po1);
            podao.add(po2);
            podao.add(po3);
            System.out.println("ProductsInOrder were added: "+podao.getAll(ProductsInOrder.class));

            Client cl = cdao.getObjById(Client.class,o2.getClientId());
            System.out.println("Order #"+o2.getId()+" for "+cl.getName()+" includes: ");
            List<ProductsInOrder> polist = podao.findListById(ProductsInOrder.class, o2.getId());
            for (ProductsInOrder productsInOrder : polist) {
                Product prod = pdao.getObjById(Product.class,productsInOrder.getProductId());
                System.out.println("- Product '" + prod.getName() + "', quantity: " + productsInOrder.getQuantity());
            }
        }
    }
}