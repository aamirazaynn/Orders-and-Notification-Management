package com.example.order_management.controllers;


import com.example.order_management.entities.*;
import com.example.order_management.services.AuthenticationService;
import com.example.order_management.services.CustomerService;
import com.example.order_management.services.OrderService;
import com.example.order_management.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final AuthenticationService authenticationService;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService , AuthenticationService authenticationService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
        this.authenticationService = authenticationService;
    }
//    List<String> productList
    @PostMapping("/placeSimpleOrder")
    public Response placeOrder(@RequestBody List<String> productList) {
        Response response = new Response();
        System.out.println("Received productList: " + productList);
        if (authenticationService.isLoggedIn() == false) {
            response.setStatus(false);
            response.setMessage("User not logged in , please login first");
            return response;
        }
        String username = authenticationService.getLoggedInCustomer().getUsername();
        if(customerService.getCustomerByUsername(username) == null){
            response.setStatus(false);
            response.setMessage("Username not found");
            return response;
        }
        Customer customer = customerService.getCustomerByUsername(username);

        ArrayList<ProductItem> productItems = new ArrayList<>();
        for (String product : productList) {
            if(productService.getProductBySerialNumber(product) == null){
                response.setStatus(false);
                response.setMessage("Product not found");
                return response;
            }
            productItems.add(productService.getProductBySerialNumber(product));
        }

        SimpleOrder order = new SimpleOrder("1", 70, customer, productItems);
        boolean res = orderService.addOrder(order);
        if(!res){
            response.setStatus(false);
            response.setMessage("Failed to place order");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Order placed successfully");
        return response;
    }

    @GetMapping("/getAllOrders")
    public ArrayList<OrderComponent> getOrders() {
        return orderService.getAllOrders();
    }
}