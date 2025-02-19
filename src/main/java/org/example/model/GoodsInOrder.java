package org.example.model;

public class GoodsInOrder {
    @Id
    private int id;
    private int order_id;
    private int good_id;
    public GoodsInOrder() {
    }

    public GoodsInOrder(int order_id, int good_id) {
        this.order_id = order_id;
        this.good_id = good_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getOrder() {
        return order_id;
    }

    public void setOrder(int order_id) {
        this.order_id = order_id;
    }

    public int getGood() {
        return good_id;
    }

    public void setGood(int good_id) {
        this.good_id = good_id;
    }
}
