package com.example.order_management.entities;

import java.util.ArrayList;

public class SimpleOrder extends OrderComponent {
    private final ArrayList<ProductItem> products = new ArrayList<>();
    float totalCost;
    String customer;

    public SimpleOrder(float shippingFees) {
        super(shippingFees);
    }
    public void addProduct(ProductItem product){
        products.add(product);
    }
    public void removeProduct(ProductItem product){
        products.remove(product);
    }
    @Override
    public OrderComponent printOrder() {
        return this;
    }
}
