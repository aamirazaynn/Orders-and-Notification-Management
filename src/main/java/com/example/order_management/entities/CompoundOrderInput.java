package com.example.order_management.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CompoundOrderInput {
    String username;
    List<String> products;

    public CompoundOrderInput(String username, List<String> products) {
        this.username = username;
        this.products = products;
    }
}
