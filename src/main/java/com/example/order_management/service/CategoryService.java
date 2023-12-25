package com.example.order_management.service;

import com.example.order_management.entities.Category;
import com.example.order_management.Common;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class CategoryService {

    public Boolean addNewCategory(Category category) {
        try {
            if(Common.ListOfCategories.get(category.getName()) != null){
                return false;
            }
            Common.ListOfCategories.put(category.getName(), category);
        } catch (Exception e) {
            System.out.println("Exception in addNewCategory as" + e.getMessage());
            return false;
        }
        return true;
    }
    public Set<String> getAllCategories() {
        try {
            Set<String> categoryNames = Common.ListOfCategories.keySet();
            return categoryNames;
        } catch (Exception e) {
            System.out.println("Exception in getAllCategories as" + e.getMessage());
        }
        return null;
    }
    public Category getCategoryByName(String name) {
        try {
            if(Common.ListOfCategories.get(name) == null){
                return null;
            }
            Category temp = Common.ListOfCategories.get(name);
            return temp;
        } catch (Exception e) {
            System.out.println("Exception in addNewCategory as" + e.getMessage());
            return null;
        }
    }
    public Boolean deleteCategoryByName(String name) {
        try {
            if(Common.ListOfCategories.get(name) == null){
                return false;
            }
            Common.ListOfCategories.remove(name);
        } catch (Exception e) {
            System.out.println("Exception in deleteCategoryByName as" + e.getMessage());
            return false;
        }
        return true;
    }
    public Boolean addItemToCategory(String serialNumber, String name) {
        try {
            if(Common.ListOfProducts.get(serialNumber) == null){
                System.out.println("Product doesn't exist: " + serialNumber);
                System.out.println("Category doesn't exist: " + name);
                return false;
            }
            if(Common.ListOfCategories.get(name) == null){
                System.out.println("Category doesn't exist: " + name);
                return false;
            }

            Common.ListOfCategories.get(name).addProduct(Common.ListOfProducts.get(serialNumber));
        } catch (Exception e) {
            System.out.println("Exception in addItemToCategory as" + e.getMessage());
            return false;
        }
        return true;
    }
    public Boolean deleteItemFromCategory(String serialNumber, String name) {
        try {
            if(Common.ListOfProducts.get(serialNumber) == null){
                return false;
            }
            if(Common.ListOfCategories.get(name) == null){
                return false;
            }
            Common.ListOfCategories.get(name).removeProduct(Common.ListOfProducts.get(serialNumber));
        } catch (Exception e) {
            System.out.println("Exception in deleteItemFromCategory as" + e.getMessage());
            return false;
        }
        return true;
    }
}
