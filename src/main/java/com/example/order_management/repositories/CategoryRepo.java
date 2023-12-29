package com.example.order_management.repositories;

import com.example.order_management.entities.Category;
import com.example.order_management.entities.ProductItem;
import com.example.order_management.services.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
@Component
public class CategoryRepo {
    private final ArrayList<Category> Categories;

    public CategoryRepo(ArrayList<Category> categories) {
        Categories = categories;
    }

    public void addCategory(Category category){
        Categories.add(category);
    }

    public Category getCategory(String name){
        for(Category category : Categories){
            if(category.getName().equals(name)){
                return category;
            }
        }
        return null;
    }

    public ArrayList<Category> getAllCategories(){
        return Categories;
    }

    public void removeCategory(String name){
        Categories.removeIf(category -> category.getName().equals(name));
    }

    public Boolean addProductToCategory(String categoryName, ProductItem productItem){
        for(Category c : Categories){
            if(c.getName().equals(categoryName)){
                c.addProduct(productItem);
                return true;
            }
        }
        return false;
    }

    public void removeProductFromCategory(String name, ProductRepo productRepo, String serialNumber){
        for(Category c : Categories){
            if(c.getName().equals(name)){
                if(productRepo.getProduct(serialNumber) != null){
                    c.removeProduct(productRepo.getProduct(serialNumber));
                } else {
                    System.out.println("Product doesn't exist: " + serialNumber);
                }
            }
        }
    }
}

