package org.example.model;

public class Client {
    @Id
    private int id;
    private String name;
    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return "Client{id="+id+", name='"+name+"'}";
    }
}
