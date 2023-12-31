package com.example.order_management.controllers;

import com.example.order_management.entities.Response;
import com.example.order_management.services.AuthenticationService;
import com.example.order_management.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cancelOrder")
public class cancelOrder {
    private final OrderService orderService;
    private final AuthenticationService authenticationService;

    public cancelOrder(OrderService orderService, AuthenticationService AuthenticationService) {
        this.orderService = orderService;
        this.authenticationService = AuthenticationService;
    }
    @PostMapping("/cancelPlacement")
    public Response cancel(@RequestBody String orderId) {
        Response response = new Response();
        if(authenticationService.isLoggedIn()){
            response.setStatus(false);
            response.setMessage("you are not logged in, please log in first");
            return response;
        }
        orderService.getOrder(orderId);

        

        return response;
    }
}
