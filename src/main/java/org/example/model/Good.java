package org.example.model;

public class Good {
    @Id
    private int id;
    private String name;
    public Good() {
    }

    public Good(String name) {
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
}
