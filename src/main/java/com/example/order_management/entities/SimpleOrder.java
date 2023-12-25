package com.example.order_management.entities;

public class SimpleOrder extends OrderComponent {
    @Override
    public OrderComponent printOrder() {
        return this;
    }
}
