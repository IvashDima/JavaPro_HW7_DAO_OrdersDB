package org.example.model;

import org.example.Id;

public class GoodsInOrder {
    @Id
    private int id;
    private Orders order;
    private Good good;
    public GoodsInOrder() {
    }

    public GoodsInOrder(Orders order, Good good) {
        this.order = order;
        this.good = good;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }
}
