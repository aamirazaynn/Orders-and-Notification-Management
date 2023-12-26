package com.example.order_management.entities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductItem{

    private String serialNumber;
    private String name;
    private String vendor;
    private String category;
    private float price;
    private int remainingNumber;

    public ProductItem print() {
        return this;
    }
}
