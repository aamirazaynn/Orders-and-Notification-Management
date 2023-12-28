package com.example.order_management.controllers;

import com.example.order_management.entities.Category;
import com.example.order_management.entities.Customer;
import com.example.order_management.services.CustomerService;
import com.example.order_management.entities.Response;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService CustomerService;
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.CustomerService = customerService;
    }

    @PostMapping("/addCustomer")
    public Response addCustomer(@RequestBody Customer customer) {
        boolean res = CustomerService.addCustomer(customer);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("username Already Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Customer added successfully");
        return response;
    }
    @GetMapping("/getCustomer/{username}")
    public Customer getCustomerByUsername(@PathVariable("username") String username) {
        return CustomerService.getCustomerByUsername(username);
    }

    @GetMapping("/getAllCustomers")
    public ArrayList<Customer> getAllCustomers() {
        return CustomerService.getAllCustomers();
    }
}