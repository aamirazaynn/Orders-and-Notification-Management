package com.example.order_management.repositories;

import com.example.order_management.entities.ProductItem;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
@Component
public class ProductRepo {
    private final ArrayList<ProductItem> Products;

    public ProductRepo(ArrayList<ProductItem> products) {
        Products = products;
    }

    public void addProduct(ProductItem product){
        Products.add(product);
        System.out.println(this.Products);
    }

    public ProductItem getProduct(String serialNumber){
        for(ProductItem product : Products){
            if(product.getSerialNumber().equals(serialNumber)){
                return product;
            }
        }
        return null;
    }

    public ArrayList<ProductItem> getAllProducts(){
        return Products;
    }

    public void removeProduct(String serialNumber){
        Products.removeIf(product -> product.getSerialNumber().equals(serialNumber));
    }

    public void updateProduct(ProductItem product){
        for(ProductItem p : Products){
            if(p.getSerialNumber().equals(product.getSerialNumber())){
                p.setCategory(product.getCategory());
                p.setPrice(product.getPrice());
                p.setName(product.getName());
                p.setRemainingNumber(product.getRemainingNumber());
                p.setVendor(product.getVendor());
            }
        }
    }

    public void printProducts(){
        for(ProductItem product : Products){
            System.out.println(product.getSerialNumber());
            System.out.println(product.getName());
        }
    }
}
