package com.example.order_management.entities;

public abstract class OrderComponent {
    float shippingFees;

    public float getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(float shippingFees) {
        this.shippingFees = shippingFees;
    }

    abstract public OrderComponent printOrder();
}
