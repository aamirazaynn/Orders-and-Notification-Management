package com.example.order_management.controllers;

import com.example.order_management.entities.*;
import com.example.order_management.services.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/placeOrder")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final AuthenticationService authenticationService;

    public OrderController(OrderService orderService, CustomerService customerService, ProductService productService , AuthenticationService authenticationService, CategoryService categoryService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.productService = productService;
        this.authenticationService = authenticationService;
        this.categoryService = categoryService;
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

        // Update remaining number of products
        for (ProductItem productItem : productItems) {
            productItem.setRemainingNumber(productItem.getRemainingNumber() - 1);
            productService.updateProductRemaining(productItem);
            categoryService.deleteItemFromCategory(productItem.getCategory(),productItem);
            categoryService.addItemToCategory(productItem, productItem.getCategory());
        }

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
        ArrayList<Map.Entry<Customer, Float>> customersInCompoundOrder = new ArrayList<>();
        ArrayList<Map.Entry<ProductItem, Integer>> productsInOrder = new ArrayList<>();
        for (CompoundOrderInput compoundOrderInput : compoundOrderInputs) {
            String customerUsername = compoundOrderInput.getUsername();
            List<String> stringSerialNumbers = compoundOrderInput.getProducts();

            // check if request is valid
            if (customerUsername == null || stringSerialNumbers == null || stringSerialNumbers.isEmpty()) {
                response.setStatus(false);
                response.setMessage("Invalid request, please provide Customer username and products");
                return response;
            }

            // check if tempCustomer exists
            Customer tempCustomer = customerService.getCustomerByUsername(customerUsername);
            if (tempCustomer == null) {
                response.setStatus(false);
                response.setMessage("Customer " + customerUsername + " not found");
                return response;
            }

            // check if product exists and add it to the list
            ArrayList<ProductItem> productItems = new ArrayList<>();
            float totalPrice= 0.0f;
            for (String serialNumber : stringSerialNumbers) {
                ProductItem tempProduct = productService.getProductBySerialNumber(serialNumber);

                // check if the product exist
                if (tempProduct == null) {
                    response.setStatus(false);
                    response.setMessage("Product " + serialNumber + "not found");
                    return response;
                }
                // check if the product in stock
                if(tempProduct.getRemainingNumber() < 1){
                    response.setStatus(false);
                    response.setMessage("Product " + serialNumber + " is out of stock");
                    return response;
                }
                productsInOrder.add(new AbstractMap.SimpleEntry<>(tempProduct, 1));
                totalPrice += tempProduct.getPrice();
                productItems.add(tempProduct);
            }

            // check if balance is enough
            if(tempCustomer.getBalance() < totalPrice){
                response.setStatus(false);
                response.setMessage(tempCustomer.getUsername() + "'s balance is not enough");
                return response;
            }

            SimpleOrder simpleOrder = new SimpleOrder(counter + "", 70, tempCustomer, productItems);
            customersInCompoundOrder.add(new AbstractMap.SimpleEntry<>(tempCustomer, totalPrice));

            compoundOrder.addOrder(simpleOrder);
            counter++;
        }

        // for loop to update balance
        for(Map.Entry<Customer, Float> entry : customersInCompoundOrder) {
            Float totalPrice = entry.getValue();
            Customer customer = entry.getKey();
            customer.setBalance(customer.getBalance() - totalPrice);
            customerService.updateCustomerBalance(customer);
        }

        // for loop to update remaining items
        for(Map.Entry<ProductItem, Integer> entry : productsInOrder){
            Integer takenNumber = entry.getValue();
            ProductItem takenProduct = entry.getKey();
            takenProduct.setRemainingNumber(takenProduct.getRemainingNumber() - takenNumber);
            productService.updateProductRemaining(takenProduct);
            categoryService.deleteItemFromCategory(takenProduct.getCategory(), takenProduct);
            categoryService.addItemToCategory(takenProduct, takenProduct.getCategory());
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