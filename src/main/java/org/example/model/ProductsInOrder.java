package org.example.model;

public class ProductsInOrder {
    @Id
    private int id;
    private int order_id;
    private int product_id;
    public ProductsInOrder() {
    }

    public ProductsInOrder(int order_id, int product_id) {
        this.order_id = order_id;
        this.product_id = product_id;
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
        return product_id;
    }

    public void setGood(int product_id) {
        this.product_id = product_id;
    }
}
