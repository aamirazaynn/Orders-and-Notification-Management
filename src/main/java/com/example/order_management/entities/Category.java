package com.example.order_management.entities;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
public class Category{
    private String name;
    private ArrayList<ProductItem> category;

    public void addProduct(ProductItem c) {
        category.add(c);
    }

    public void removeProduct(ProductItem c) {
        category.remove(c);
    }

    public Category print() {
        return this;
    }
}
