package com.example.order_management.controllers;

import com.example.order_management.entities.*;
import com.example.order_management.services.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/shipOrder")
public class ShippingController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final NotificationService notificationService;
    private final AuthenticationService authenticationService;

    public ShippingController(OrderService orderService, CustomerService customerService, NotificationService notificationService, AuthenticationService authenticationService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.notificationService = notificationService;

        this.authenticationService = authenticationService;
    }

    @PostMapping("")
    public Response shipOrder(@RequestBody String orderId) {
        Response response = new Response();
        // Check if user is logged in
        if (!authenticationService.isLoggedIn()) {
            response.setStatus(false);
            response.setMessage("User not logged in, please login first");
            return response;
        }
        OrderComponent order = orderService.getOrder(orderId);
        List<Float> shippingFeesList = new ArrayList<>();
        if(order == null){
            response.setStatus(false);
            response.setMessage("Order not found");
            return response;
        }
        else if (orderService.isShipped(orderId)) {
            response.setStatus(false);
            response.setMessage("Order already shipped");
            return response;

        }
        else {
            float shippingFees = order.getShippingFees();
            for (Customer customer : order.getAllCustomers()) {
                if(customer.getBalance() < shippingFees/order.getAllCustomers().size()){
                    response.setStatus(false);
                    response.setMessage("Customer " + customer.getUsername() + "'s balance is not enough");
                    return response;
                }
                else {
                    shippingFeesList.add(shippingFees/order.getAllCustomers().size());
                }
            }
            for (int i = 0; i < shippingFeesList.size(); i++) {
                Customer customer = order.getAllCustomers().get(i);
                customer.setBalance(customer.getBalance() - shippingFeesList.get(i));
                customerService.updateCustomerBalance(customer);
            }

//            String username = order.getAllCustomers().get(0).getUsername();
//            String language = customerService.getCustomerByUsername(username).getLanguage();
//            String channel = customerService.getCustomerByUsername(username).getChannel();
//            notificationService.addNotification(username,orderId,language,"shipping", channel);

            String username = authenticationService.getLoggedInCustomer().getUsername();
            String language = authenticationService.getLoggedInCustomer().getLanguage();
            String channel = authenticationService.getLoggedInCustomer().getChannel();
            notificationService.addNotification(username,orderId,language,"shipping", channel);
            response.setStatus(true);
            response.setMessage("Order shipped successfully");
            orderService.shipOrder(orderId);
        }
        return response;
    }
}