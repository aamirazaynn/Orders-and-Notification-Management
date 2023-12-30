package com.example.order_management.controllers;

import com.example.order_management.entities.*;
import com.example.order_management.services.AuthenticationService;
import com.example.order_management.services.CustomerService;
import com.example.order_management.services.OrderService;
import com.example.order_management.services.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/placeOrder")
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
    @PostMapping("/simpleOrder")
    public Response placeSimpleOrder(@RequestBody List<String> productList) {
        Response response = new Response();
        float totalPrice = 0;

        // Check if user is logged in
        if (!authenticationService.isLoggedIn()) {
            response.setStatus(false);
            response.setMessage("User not logged in , please login first");
            return response;
        }

        // Check if user exists
        String username = authenticationService.getLoggedInCustomer().getUsername();
        if(customerService.getCustomerByUsername(username) == null){
            response.setStatus(false);
            response.setMessage("Username not found");
            return response;
        }

        Customer customer = customerService.getCustomerByUsername(username);

        // Check if product exists and add products to the list
        ArrayList<ProductItem> productItems = new ArrayList<>();
        for (String product : productList) {
            if(productService.getProductBySerialNumber(product) == null){
                response.setStatus(false);
                response.setMessage("Product " + product + "not found");
                return response;
            }
            totalPrice += productService.getProductBySerialNumber(product).getPrice();
            productItems.add(productService.getProductBySerialNumber(product));
        }

        // Check if balance is enough
        if(customer.getBalance() < totalPrice){
            response.setStatus(false);
            response.setMessage("Your balance is not enough");
            return response;
        }

        // Update balance
        customer.setBalance(customer.getBalance() - totalPrice);
        customerService.updateCustomerBalance(customer);

        // Add order
        SimpleOrder order = new SimpleOrder(orderService.getAllOrders().size() + 1 + "", 70, customer, productItems);
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

    @PostMapping("/compoundOrder")
    public Response placeCompoundOrder(@RequestBody List<CompoundOrderInput> compoundOrderInputs) {
        Response response = new Response();

        // Check if user is logged in
        if (!authenticationService.isLoggedIn()) {
            response.setStatus(false);
            response.setMessage("User not logged in, please login first");
            return response;
        }

        CompoundOrder compoundOrder = new CompoundOrder(180);

        // loop for every simple order in the compound order
        int counter = 1;
        ArrayList<Map.Entry<Float, Customer>> customersInCompoundOrder = new ArrayList<>();
        for (CompoundOrderInput compoundOrderInput : compoundOrderInputs) {
            String customerUsername = compoundOrderInput.getUsername();
            List<String> products = compoundOrderInput.getProducts();

            // check if request is valid
            if (customerUsername == null || products == null || products.isEmpty()) {
                response.setStatus(false);
                response.setMessage("Invalid request, please provide customer username and products");
                return response;
            }

            // check if customer exists
            Customer customer = customerService.getCustomerByUsername(customerUsername);
            if (customer == null) {
                response.setStatus(false);
                response.setMessage("Customer " + customerUsername + " not found");
                return response;
            }

            // check if product exists and add it to the list
            ArrayList<ProductItem> productItems = new ArrayList<>();
            float totalPrice= 0.0f;
            for (String product : products) {
                if (productService.getProductBySerialNumber(product) == null) {
                    response.setStatus(false);
                    response.setMessage("Product " + product + "not found");
                    return response;
                }
                productItems.add(productService.getProductBySerialNumber(product));
                totalPrice += productService.getProductBySerialNumber(product).getPrice();
            }

            // check if balance is enough
            if(customer.getBalance() < totalPrice){
                response.setStatus(false);
                response.setMessage(customer.getUsername() + "'s balance is not enough");
                return response;
            }

            customersInCompoundOrder.add(new AbstractMap.SimpleEntry<>(totalPrice, customer));
            SimpleOrder order = new SimpleOrder(counter + "", 70, customer, productItems);
            compoundOrder.addOrder(order);
            counter++;
        }

        // for loop to update balance
        for(Map.Entry<Float, Customer> entry : customersInCompoundOrder) {
            Float totalPrice = entry.getKey();
            Customer customer = entry.getValue();
            customer.setBalance(customer.getBalance() - totalPrice);
            customerService.updateCustomerBalance(customer);
        }

        compoundOrder.setId(orderService.getAllOrders().size() + 1 + "");
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