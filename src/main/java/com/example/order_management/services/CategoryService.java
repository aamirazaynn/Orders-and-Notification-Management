package com.example.order_management.services;

import com.example.order_management.repositories.ProductRepo;
import com.example.order_management.repositories.CategoryRepo;
import com.example.order_management.entities.Category;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CategoryService {
    private final CategoryRepo categoryRepo;
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    public Boolean addNewCategory(Category category) {
        try {
            if(categoryRepo.getCategory(category.getName()) != null){
                return false;
            }
            categoryRepo.addCategory(category);
        } catch (Exception e) {
            System.out.println("Exception in addNewCategory as" + e.getMessage());
            return false;
        }
        return true;
    }
    public ArrayList<String> getAllCategories() {
        try {
            ArrayList<String> categoryNames = new ArrayList<>();
            ArrayList<Category> temp = categoryRepo.getAllCategories();
            for (Category category : temp) {
                categoryNames.add(category.getName());
            }
            return categoryNames;
        } catch (Exception e) {
            System.out.println("Exception in getAllCategories as" + e.getMessage());
        }
        return null;
    }
    public Category getCategoryByName(String name) {
        try {
            if(categoryRepo.getCategory(name) == null){
                return null;
            }
            return categoryRepo.getCategory(name);
        } catch (Exception e) {
            System.out.println("Exception in addNewCategory as" + e.getMessage());
            return null;
        }
    }
    public Boolean deleteCategoryByName(String name) {
        try {
            if(categoryRepo.getCategory(name) == null){
                return false;
            }
           categoryRepo.removeCategory(name);
        } catch (Exception e) {
            System.out.println("Exception in deleteCategoryByName as" + e.getMessage());
            return false;
        }
        return true;
    }
    public Boolean addItemToCategory(String serialNumber, String name, ProductRepo productRepo) {
        try {
            if(categoryRepo.getCategory(name) == null){
                System.out.println("Category doesn't exist: " + name);
                return false;
            }
            System.out.println("out");
            if(productRepo.getProduct(serialNumber) == null){
                System.out.println("Product doesn't exist: " + serialNumber);
                return false;
            }
            categoryRepo.addProductToCategory(name, productRepo, serialNumber);
        } catch (Exception e) {
            System.out.println("Exception in addItemToCategory as" + e.getMessage());
            return false;
        }
        return true;
    }
    public Boolean deleteItemFromCategory(String serialNumber, String name, ProductRepo ProductRepo) {
        try {
            if(categoryRepo.getCategory(name) == null){
                return false;
            }
            categoryRepo.removeProductFromCategory(name, ProductRepo, serialNumber);
        } catch (Exception e) {
            System.out.println("Exception in deleteItemFromCategory as" + e.getMessage());
            return false;
        }
        return true;
    }
}
