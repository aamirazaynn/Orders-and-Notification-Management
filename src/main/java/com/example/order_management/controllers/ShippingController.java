package com.example.order_management.controllers;

import com.example.order_management.entities.*;
import com.example.order_management.services.AuthenticationService;
import com.example.order_management.services.CustomerService;
import com.example.order_management.services.OrderService;
import com.example.order_management.services.ProductService;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/shipOrder")
public class ShippingController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final AuthenticationService authenticationService;

    public ShippingController(OrderService orderService, CustomerService customerService, ProductService productService , AuthenticationService authenticationService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("")
    public Response shipOrder(@RequestBody String orderId) {
        Response response = new Response();
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
                else
                {
                    shippingFeesList.add(shippingFees/order.getAllCustomers().size());
                }
            }
            for (int i = 0; i < shippingFeesList.size(); i++) {
                Customer customer = order.getAllCustomers().get(i);
                customer.setBalance(customer.getBalance() - shippingFeesList.get(i));
                customerService.updateCustomerBalance(customer);
            }
            response.setStatus(true);
            response.setMessage("Order shipped successfully");
            orderService.shipOrder(orderId);
        }
        return response;
    }
}
