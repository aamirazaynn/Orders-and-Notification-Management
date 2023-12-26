package com.example.order_management.controller;
import com.example.order_management.entities.ProductItem;
import com.example.order_management.service.ProductService;
import com.example.order_management.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public Response addProduct(@RequestBody ProductItem product) {
        boolean res = productService.addProduct(product);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Serial Number Already Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Product created successfully");
        return response;
    }

    @GetMapping("/getAllProducts")
    public ArrayList<ProductItem> getAllProducts() {
        return productService.getAllProducts();
    }
}