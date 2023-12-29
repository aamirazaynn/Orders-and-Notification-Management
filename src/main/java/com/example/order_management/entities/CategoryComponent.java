package com.example.order_management.entities;

public abstract class CategoryComponent {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract CategoryComponent print();


}
