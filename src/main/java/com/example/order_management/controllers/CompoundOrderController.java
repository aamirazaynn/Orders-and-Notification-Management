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
@RequestMapping("/compoundOrder")
public class CompoundOrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final AuthenticationService authenticationService;

    public CompoundOrderController(OrderService orderService, CustomerService customerService, ProductService productService , AuthenticationService authenticationService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
        this.authenticationService = authenticationService;
    }
    @PostMapping("/placeCompoundOrder")
    public Response placeCompoundOrder(@RequestBody List<CompoundOrderInput> compoundOrderInputs) {
        Response response = new Response();
        if (!authenticationService.isLoggedIn()) {
            response.setStatus(false);
            response.setMessage("User not logged in, please login first");
            return response;
        }

        String mainUsername = authenticationService.getLoggedInCustomer().getUsername();
        Customer mainCustomer = customerService.getCustomerByUsername(mainUsername);

        if (mainCustomer == null) {
            response.setStatus(false);
            response.setMessage("Main customer not found");
            return response;
        }

        CompoundOrder compoundOrder = new CompoundOrder(180);
        for (CompoundOrderInput compoundOrderInput : compoundOrderInputs) {
            String customerUsername = compoundOrderInput.getUsername();
            List<String> products = compoundOrderInput.getProducts();

            if (customerUsername == null || products == null || products.isEmpty()) {
                response.setStatus(false);
                response.setMessage("Invalid request, please provide customer username and products");
                return response;
            }

            Customer customer = customerService.getCustomerByUsername(customerUsername);
            if (customer == null) {
                response.setStatus(false);
                response.setMessage("Customer " + customerUsername + " not found");
                return response;
            }
            ArrayList<ProductItem> productItems = new ArrayList<>();
            for (String product : products) {
                if (productService.getProductBySerialNumber(product) == null) {
                    response.setStatus(false);
                    response.setMessage("Product " + product + "not found");
                    return response;
                }
                productItems.add(productService.getProductBySerialNumber(product));
            }

            SimpleOrder order = new SimpleOrder("1", 70, customer, productItems);
            compoundOrder.addOrder(order);
        }
        boolean res = orderService.addOrder(compoundOrder);
        if (!res) {
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