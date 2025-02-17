package org.example;

public class GoodsInOrder {
    @Id
    private int id;
    private Order order;
    private Good good;
    public GoodsInOrder() {
    }

    public GoodsInOrder(Order order, Good good) {
        this.order = order;
        this.good = good;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Good getGood() {
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }
}
