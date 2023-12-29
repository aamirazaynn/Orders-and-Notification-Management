package com.example.order_management.services;

import com.example.order_management.entities.Customer;
import com.example.order_management.repositories.AuthenticationRepo;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationRepo authenticationRepo;

    public AuthenticationService(AuthenticationRepo authenticationRepo) {
        this.authenticationRepo = authenticationRepo;
    }

    public boolean login(String username, String password) {
        return authenticationRepo.logIn(username, password);
    }

    public void logout() {
        authenticationRepo.logOut();
    }

    public boolean isLoggedIn() {
        return authenticationRepo.isLoggedIn();
    }

    public Customer getLoggedInCustomer() {
        return authenticationRepo.getLoggedInCustomer();
    }
}
