package com.example.order_management.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class SimpleOrder extends OrderComponent {
    @Getter
    private final ArrayList<ProductItem> products = new ArrayList<>();
    Customer customer;

    public SimpleOrder(String id,float shippingFees, Customer customer, ArrayList<ProductItem> products) {
        super(shippingFees);
        this.customer = customer;
        this.products.addAll(products);
        this.id = id;
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
    @Override
    public float calcShipping() {
        super.shippingFees = 0;
        for (ProductItem product : products) {
            super.shippingFees += product.getPrice();
        }
        return super.shippingFees;
    }
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        return customers;
    }
    @Override
    public Map<String, ArrayList<ProductItem>> getAllProductsForOutput() {
        Map<String, ArrayList<ProductItem>> temp = new HashMap<>();
        temp.put(customer.getUsername(), this.products);
        return temp;
    }

}
