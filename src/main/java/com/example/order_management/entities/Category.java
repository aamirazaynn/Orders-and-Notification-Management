package com.example.order_management.entities;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
public class Category{
    private String name;
    private ArrayList<ProductItem> products;

    public void addProduct(ProductItem c) {
        products.add(c);
    }

    public void removeProduct(ProductItem c) {
        products.remove(c);
    }

    public Category print() {
        return this;
    }
}
