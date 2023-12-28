package com.example.order_management.entities;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Customer {

    private String username;
    private String password;
    private String phoneNumber;
    private String location;
    private float balance;


}
