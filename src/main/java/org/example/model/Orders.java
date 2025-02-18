package org.example.model;

import org.example.Id;
import org.example.model.Client;

public class Orders {
    @Id
    private int id;

    private Client client;
//    private GoodsInOrder goods;

    public Orders() {
    }

    public Orders(Client client) { //, GoodsInOrder goods
        this.client = client;
//        this.goods = goods;
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

//    public GoodsInOrder getGoodsInOrder() {
//        return goods;
//    }
//
//    public void setGoodsInOrder(GoodsInOrder goods) {
//        this.goods = goods;
//    }


}
