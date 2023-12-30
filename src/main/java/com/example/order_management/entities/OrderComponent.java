package com.example.order_management.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public abstract class OrderComponent {
    protected float shippingFees;
    protected String id;
    protected boolean isShipped;

    public OrderComponent(float shippingFees) {
        this.shippingFees = shippingFees;
    }

    abstract public OrderComponent printOrder();

    abstract public float calcShipping();

    abstract public List<Customer> getAllCustomers();
}
