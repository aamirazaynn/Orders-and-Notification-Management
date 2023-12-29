package com.example.order_management.controllers;


import com.example.order_management.entities.*;
import com.example.order_management.services.CustomerService;
import com.example.order_management.services.OrderService;
import com.example.order_management.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
    }

    @PostMapping("/placeSimpleOrder/{username}")
    public Response placeOrder(@RequestBody ArrayList<String> productList, @PathVariable("username") String username) {
        Response response = new Response();

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