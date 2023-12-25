package com.example.order_management.entities;

import lombok.Getter;

import java.util.ArrayList;

public class Category{
    @Getter
    private String name;
    private ArrayList<ProductItem> categories;

    public void addProduct(ProductItem c) {
        categories.add(c);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void removeProduct(ProductItem c) {
        categories.remove(c);
    }

    public int getRemaining() {
        return categories.size();
    }

    public Category print() {
        return this;
    }
}
