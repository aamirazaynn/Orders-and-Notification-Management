package com.example.order_management.entities;

import java.util.ArrayList;

public class Category extends CategoryComponent {

    private ArrayList<CategoryComponent> categories;

    public void addCategory(CategoryComponent c) {
        categories.add(c);
    }

    public void removeCategory(CategoryComponent c) {
        categories.remove(c);
    }

    public int getRemaining() {
        return categories.size();
    }

    @Override
    public CategoryComponent print() {
        return this;
    }
}
