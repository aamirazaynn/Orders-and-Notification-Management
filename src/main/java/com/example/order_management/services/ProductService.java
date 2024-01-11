package com.example.order_management.services;

import com.example.order_management.entities.ProductItem;
import com.example.order_management.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    private final ProductRepo ProductRepo;

    public ProductService(ProductRepo ProductRepo) {
        this.ProductRepo = ProductRepo;
    }
    public Boolean addProduct(ProductItem product) {
        try {
            if(ProductRepo.getProduct(product.getSerialNumber()) != null){
                System.out.println("Product already exists: " + product.getSerialNumber());
                return false;
            }
            ProductRepo.addProduct(product);
        } catch (Exception e) {
            System.out.println("Exception in addProduct as" + e.getMessage());
            return false;
        }
        return true;
    }
    public ArrayList<ProductItem> getAllProducts() {
        try {
            ArrayList<ProductItem> products = new ArrayList<>();
            for (ProductItem product : ProductRepo.getAllProducts()) {
                if (product.getRemainingNumber()>0) {
                    products.add(product);
                }
            }
            return products;
        } catch (Exception e) {
            System.out.println("Exception in getAllProducts as" + e.getMessage());
        }
        return null;
    }
    public ProductItem getProductBySerialNumber(String serialNumber) {
        try {
            if(ProductRepo.getProduct(serialNumber) == null){
                return null;
            }
            return ProductRepo.getProduct(serialNumber);
        } catch (Exception e) {
            System.out.println("Exception in getProductBySerialNumber as" + e.getMessage());
            return null;
        }
    }
    public void updateProductRemaining(ProductItem product){
        ProductRepo.updateProductRemaining(product);
    }

}
