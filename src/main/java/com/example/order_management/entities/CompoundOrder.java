package com.example.order_management.entities;

import java.util.ArrayList;

public class CompoundOrder extends OrderComponent {
    ArrayList<OrderComponent> orderComponents = new ArrayList<>();

    public CompoundOrder(float shippingFees) {
        super(shippingFees);
    }
    public void addOrder(OrderComponent orderComponent) {
        orderComponents.add(orderComponent);
    }
    public void removeOrder(OrderComponent orderComponent) {
        orderComponents.remove(orderComponent);
    }
    public OrderComponent getOrder(int index) {
        return orderComponents.get(index);
    }
    @Override
    public OrderComponent printOrder() {
        return this;
    }
}
