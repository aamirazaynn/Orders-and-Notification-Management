package com.example.order_management.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class CompoundOrder extends OrderComponent {
    ArrayList<SimpleOrder> orderComponents = new ArrayList<>();

    public CompoundOrder(float shippingFees) {
        super(shippingFees);
    }
    public void addOrder(SimpleOrder orderComponent) {
        orderComponents.add(orderComponent);
    }
    public void removeOrder(SimpleOrder orderComponent) {
        orderComponents.remove(orderComponent);
    }
    public OrderComponent getOrder(int index) {
        return orderComponents.get(index);
    }
    @Override
    public OrderComponent printOrder() {
        return this;
    }
    @Override
    public float calcShipping() {
        super.shippingFees = 0;
        for (OrderComponent orderComponent : orderComponents) {
            super.shippingFees += orderComponent.calcShipping();
        }
        return super.shippingFees;
    }
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (OrderComponent orderComponent : orderComponents) {
            customers.addAll(orderComponent.getAllCustomers());
        }
        return customers;
    }
    @Override
    public Map<String, ArrayList<ProductItem>> getAllProductsForOutput() {
        Map<String, ArrayList<ProductItem>> temp = new HashMap<>();
        for (SimpleOrder orderComponent : orderComponents) {
            temp.put(orderComponent.getCustomer().getUsername(), orderComponent.getProducts());
        }
        return temp;
    }
}