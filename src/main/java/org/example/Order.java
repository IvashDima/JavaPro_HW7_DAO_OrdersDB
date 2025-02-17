package org.example;

import java.util.List;

public class Order {
    @Id
    private int id;

    private Client client;
    private GoodsInOrder goods;

    public Order() {
    }

    public Order(Client client, GoodsInOrder goods) {
        this.client = client;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public GoodsInOrder getGoodsInOrder() {
        return goods;
    }

    public void setGoodsInOrder(GoodsInOrder goods) {
        this.goods = goods;
    }


}
