package com.example.order_management.controllers;

import com.example.order_management.entities.Customer;
import com.example.order_management.entities.OrderComponent;
import com.example.order_management.entities.Response;
import com.example.order_management.services.AuthenticationService;
import com.example.order_management.services.CustomerService;
import com.example.order_management.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/cancelOrder")
public class cancelOrder {
    private final OrderService orderService;
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;

    public cancelOrder(OrderService orderService, AuthenticationService AuthenticationService, CustomerService customerService) {
        this.orderService = orderService;
        this.authenticationService = AuthenticationService;
        this.customerService = customerService;
    }
    @PostMapping("/cancelPlacement")
    public Response cancelPlacement(@RequestBody String orderId) {
        Response response = new Response();
        OrderComponent order = orderService.getOrder(orderId);

        // check if user is logged in
        if(!authenticationService.isLoggedIn()){
            response.setStatus(false);
            response.setMessage("You are not logged in, please log in first");
            return response;
        }

        // check if order exists
        if(order == null){
            response.setStatus(false);
            response.setMessage("Order id not found");
            return response;
        }

        // check if order is shipped
        if(order.isShipped()){
            response.setStatus(false);
            response.setMessage("Order is already shipped");
            return response;
        }

        Map<String, Float> orderTotalCost = order.calcTotalCost();

        for(Map.Entry<String, Float> entry : orderTotalCost.entrySet()){
            Customer customer = customerService.getCustomerByUsername(entry.getKey());
            customer.setBalance(customer.getBalance() + entry.getValue());
            customerService.updateCustomerBalance(customer);
        }

        orderService.removeOrder(orderId);

        response.setMessage("Order cancelled successfully");
        response.setStatus(true);

        return response;
    }
}
