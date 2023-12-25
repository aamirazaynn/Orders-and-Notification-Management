package com.example.order_management.entities;

public class CompoundOrder extends OrderComponent {
    @Override
    public OrderComponent printOrder() {
        return this;
    }
}
