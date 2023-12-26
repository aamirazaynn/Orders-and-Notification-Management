package com.example.order_management;

import com.example.order_management.entities.Category;
import com.example.order_management.entities.ProductItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class Repo {
    public static Map<String, ProductItem> ListOfProducts = new HashMap<String,ProductItem>();
    public static Map<String, Category> ListOfCategories = new HashMap<String, Category>();

    public static void addProduct(ProductItem product){
        ListOfProducts.put(product.getSerialNumber(), product);
    }

    public static void addCategory(Category category){
        ListOfCategories.put(category.getName(), category);
    }

    public static ProductItem getProduct(String serialNumber){
        return ListOfProducts.get(serialNumber);
    }

    public static Category getCategory(String name){
        return ListOfCategories.get(name);
    }

    public static ArrayList<ProductItem> getAllProducts(){
        return new ArrayList<ProductItem>(ListOfProducts.values());
    }

    public static ArrayList<Category> getAllCategories(){
        return new ArrayList<Category>(ListOfCategories.values());
    }

    public static void removeProduct(String serialNumber){
        ListOfProducts.remove(serialNumber);
    }

    public static void removeCategory(String name){
        ListOfCategories.remove(name);
    }

    public static void updateProduct(ProductItem product){
        ListOfProducts.put(product.getSerialNumber(), product);
    }

    public static void updateCategory(Category category){
        ListOfCategories.put(category.getName(), category);
    }
}
