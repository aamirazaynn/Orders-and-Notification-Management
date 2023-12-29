package com.example.order_management.controllers;

import com.example.order_management.entities.ProductItem;
import com.example.order_management.services.CategoryService;
import com.example.order_management.services.ProductService;
import com.example.order_management.entities.Response;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {
    ProductService productService;
    CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @PostMapping("/addProduct")
    public Response addProduct(@RequestBody ProductItem product) {
        Response response = new Response();

        if(categoryService.getCategoryByName(product.getCategory()) == null) {
            response.setStatus(false);
            response.setMessage("Category Name Doesn't Exists");
            return response;
        }

        boolean res = productService.addProduct(product);

        if (!res) {
            response.setStatus(false);
            response.setMessage("Serial Number Already Exists");
            return response;
        }
        categoryService.addItemToCategory(product, product.getCategory());
        response.setStatus(true);
        response.setMessage("Product added successfully");
        return response;
    }
    @GetMapping("/getAllProducts")
    public ArrayList<ProductItem> getAllProducts() {
        return productService.getAllProducts();
    }
}