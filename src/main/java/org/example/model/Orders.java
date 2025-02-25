package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    @Id
    private int id;
    private int client_id;
//    private List<ProductsInOrder> products = new ArrayList<>();;
    public Orders() {}

    public Orders(int client_id) {
        this.client_id = client_id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getClientId() {
        return client_id;
    }
    public void setClientId(int client_id) {
        this.client_id = client_id;
    }
//    public List<ProductsInOrder>  getProducts() {
//        return products;
//    }
//    public void addProducts(ProductsInOrder product) {
//        products.add(product);
//    }
    @Override
    public String toString(){
        return "Order{id="+id+", " +
                "client_id="+client_id+"}";
    }
}
