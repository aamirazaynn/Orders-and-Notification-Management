package com.example.order_management.controllers;

import com.example.order_management.entities.Customer;
import com.example.order_management.entities.OrderComponent;
import com.example.order_management.entities.Response;
import com.example.order_management.services.AuthenticationService;
import com.example.order_management.services.CustomerService;
import com.example.order_management.services.NotificationService;
import com.example.order_management.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cancelOrder")
public class cancelOrder {
    private final OrderService orderService;
    private final AuthenticationService authenticationService;
    private final CustomerService customerService;
    private final NotificationService notificationService;

    public cancelOrder(OrderService orderService, AuthenticationService AuthenticationService, CustomerService customerService, NotificationService notificationService) {
        this.orderService = orderService;
        this.authenticationService = AuthenticationService;
        this.customerService = customerService;
        this.notificationService = notificationService;
    }
    @PostMapping("/cancelPlacement")
    public Response cancelPlacement(@RequestBody String orderId) {
        Response response = new Response();
        OrderComponent order = orderService.getOrder(orderId);
        Customer loggedInCustomer = authenticationService.getLoggedInCustomer();
        List<Customer> orderCustomers = order.getAllCustomers();
        boolean isOrderCustomer = false;

        // check if user is logged in
        if(!authenticationService.isLoggedIn()){
            response.setStatus(false);
            response.setMessage("You are not logged in, please log in first");
            return response;
        }

        // check if user is order customer
        for(Customer c : orderCustomers){
            if(c.getUsername().equals(loggedInCustomer.getUsername())){
                isOrderCustomer = true;
                break;
            }
        }
        if(!isOrderCustomer){
            response.setStatus(false);
            response.setMessage("This order is not yours");
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

        // remove order
        orderService.removeOrder(orderId);

        // add notification
        String username = authenticationService.getLoggedInCustomer().getUsername();
        String language = authenticationService.getLoggedInCustomer().getLanguage();
        String channel = authenticationService.getLoggedInCustomer().getChannel();
        notificationService.addNotification(username, orderId, language, "cancel placement", channel);

        response.setMessage("Order cancelled successfully");
        response.setStatus(true);
        return response;
    }
}
