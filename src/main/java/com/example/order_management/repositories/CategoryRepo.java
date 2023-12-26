package com.example.order_management.repositories;
import com.example.order_management.entities.Category;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;


@Repository
public class CategoryRepo {
    private final ArrayList<Category> Categories;

    public CategoryRepo() {
        Categories = new ArrayList<Category>();
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
        for(Category category : Categories){
            if(category.getName().equals(name)){
                Categories.remove(category);
            }
        }
    }

    public void updateCategory(Category category){
        for(Category c : Categories){
            if(c.getName().equals(category.getName())){
                c.setName(category.getName());
            }
        }
    }
}

