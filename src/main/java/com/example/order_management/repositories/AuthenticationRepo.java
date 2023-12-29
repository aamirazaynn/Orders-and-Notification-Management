package com.example.order_management.repositories;

import com.example.order_management.entities.Customer;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@Repository
public class AuthenticationRepo {
    private CustomerRepo customerRepo;

    private Customer loggedInCustomer;

    public AuthenticationRepo(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
        this.loggedInCustomer = null;
    }
    public boolean logIn(String username, String password) {
        List<Customer> customers = customerRepo.getAllCustomers();
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username) && customer.getPassword().equals(password)) {
                loggedInCustomer = customer;
                return true;
            }
        }
        return false;
    }
    public void logOut() {
        loggedInCustomer = null;
    }
    public boolean isLoggedIn() {
        return loggedInCustomer != null;
    }
}
