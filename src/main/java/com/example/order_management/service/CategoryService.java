package com.example.order_management.service;
import com.example.order_management.repositories.ProductRepo;
import com.example.order_management.repositories.CategoryRepo;
import com.example.order_management.entities.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class CategoryService {

    private final CategoryRepo Repo = new CategoryRepo();
    private final ProductRepo ProductRepo = new ProductRepo();
    public Boolean addNewCategory(Category category) {
        try {
            if(Repo.getCategory(category.getName()) != null){
                return false;
            }
            Repo.addCategory(category);
        } catch (Exception e) {
            System.out.println("Exception in addNewCategory as" + e.getMessage());
            return false;
        }
        return true;
    }
    public ArrayList<String> getAllCategories() {
        try {
            ArrayList<String> categoryNames = new ArrayList<>();
            ArrayList<Category> temp = Repo.getAllCategories();
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
            if(Repo.getCategory(name) == null){
                return null;
            }
            Category temp = Repo.getCategory(name);
            return temp;
        } catch (Exception e) {
            System.out.println("Exception in addNewCategory as" + e.getMessage());
            return null;
        }
    }
    public Boolean deleteCategoryByName(String name) {
        try {
            if(Repo.getCategory(name) == null){
                return false;
            }
           Repo.removeCategory(name);
        } catch (Exception e) {
            System.out.println("Exception in deleteCategoryByName as" + e.getMessage());
            return false;
        }
        return true;
    }
    public Boolean addItemToCategory(String serialNumber, String name) {
        try {
            if(ProductRepo.getProduct(serialNumber) == null){
                System.out.println("Product doesn't exist: " + serialNumber);
                System.out.println("Category doesn't exist: " + name);
                return false;
            }
            if(Repo.getCategory(name) == null){
                System.out.println("Category doesn't exist: " + name);
                return false;
            }

            Repo.getCategory(name).addProduct(ProductRepo.getProduct(serialNumber));
        } catch (Exception e) {
            System.out.println("Exception in addItemToCategory as" + e.getMessage());
            return false;
        }
        return true;
    }
    public Boolean deleteItemFromCategory(String serialNumber, String name) {
        try {
            if(ProductRepo.getProduct(serialNumber) == null){
                return false;
            }
            if(Repo.getCategory(name) == null){
                return false;
            }
            Repo.getCategory(name).removeProduct(ProductRepo.getProduct(serialNumber));
        } catch (Exception e) {
            System.out.println("Exception in deleteItemFromCategory as" + e.getMessage());
            return false;
        }
        return true;
    }
}
