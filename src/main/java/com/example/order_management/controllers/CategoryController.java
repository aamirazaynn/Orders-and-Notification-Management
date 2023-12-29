package com.example.order_management.controllers;

import com.example.order_management.entities.Category;
import com.example.order_management.entities.Response;
import com.example.order_management.repositories.ProductRepo;
import com.example.order_management.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategory")
    public Response addNewCategory(@RequestBody Category category) {
        boolean res = categoryService.addNewCategory(category);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Category name already exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Category created successfully");
        return response;
    }
    @GetMapping("/getAllCategories")
    public ArrayList<String> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/getCategory/{categoryName}")
    public Category getCategoryByName(@PathVariable("categoryName") String name) {
        return categoryService.getCategoryByName(name);
    }
    @DeleteMapping("/deleteCategory/{categoryName}")
    public Response deleteCategoryByName(@PathVariable("categoryName") String name) {
        boolean res = categoryService.deleteCategoryByName(name);
        Response response = new Response();
        if (!res) {
            response.setStatus(false);
            response.setMessage("Category Doesn't Exists");
            return response;
        }

        response.setStatus(true);
        response.setMessage("Category deleted successfully");
        return response;
    }
    @DeleteMapping("/deleteItemFromCategory/{categoryName}")
    public Response deleteItemFromCategory(@RequestBody String serialNumber, @PathVariable("categoryName") String name) {
        //boolean res = categoryService.deleteItemFromCategory(serialNumber, name, productRepo);
        Response response = new Response();
        //if (!res) {
            response.setStatus(false);
            response.setMessage("Product or category Doesn't Exists");
            //return response;
        //}

        response.setStatus(true);
        response.setMessage("Product deleted from category successfully");
        return response;
    }
}
