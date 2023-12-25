package com.example.order_management.entities;

import java.util.ArrayList;

public class SimpleOrder extends OrderComponent {
    ArrayList<OrderComponent> products = new ArrayList<>();
    float totalCost;
    String customer;

    public SimpleOrder(float shippingFees) {
        super(shippingFees);
    }
    public void addProduct(OrderComponent product){
        products.add(product);
    }
    public void removeProduct(OrderComponent product){
        products.remove(product);
    }
    @Override
    public OrderComponent printOrder() {
        return this;
    }
}
