package org.example.model;

public class ProductsInOrder {
    @Id
    private int id;
    private int order_id;
    private int product_id;
    private int quantity;
    public ProductsInOrder() {
    }

    public ProductsInOrder(int order_id, int product_id, int quantity) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderId() {
        return order_id;
    }
    public void setOrderId(int order_id) {
        this.order_id = order_id;
    }
    public int getProductId() {
        return product_id;
    }
    public void setProductId(int product_id) {
        this.product_id = product_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString(){
        return "ProductsInOrder{id="+id+", " +
                "order_id="+order_id+", " +
                "product_id="+product_id+", " +
                "quantity="+quantity+"}";
    }
}
