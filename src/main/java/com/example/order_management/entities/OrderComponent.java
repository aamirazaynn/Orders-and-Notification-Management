package com.example.order_management.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class OrderComponent {
    private float shippingFees;

    public OrderComponent(float shippingFees) {
        this.shippingFees = shippingFees;
    }

    abstract public OrderComponent printOrder();
}
