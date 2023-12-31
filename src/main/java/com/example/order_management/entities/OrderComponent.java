package com.example.order_management.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    abstract public Map<String, ArrayList<ProductItem>> getAllProductsForOutput();
    abstract public Map<String, Float> calcTotalCost();
}
