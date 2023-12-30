package com.example.order_management.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Authentication {

    String username;
    String password;

    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
