package org.example.model;

public class Orders {
    @Id
    private int id;

    private int client_id;
//    private GoodsInOrder goods;

    public Orders() {
    }

    public Orders(int client_id) { //, GoodsInOrder goods
        this.client_id = client_id;
//        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getClient() {
        return client_id;
    }

    public void setClient(int client_id) {
        this.client_id = client_id;
    }

//    public GoodsInOrder getGoodsInOrder() {
//        return goods;
//    }
//
//    public void setGoodsInOrder(GoodsInOrder goods) {
//        this.goods = goods;
//    }


}
