package org.example.model;

public class Orders {
    @Id
    private int id;
    private int client_id;
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
    @Override
    public String toString(){
        return "Order{id="+id+", " +
                "client_id="+client_id+"}";
    }
}
