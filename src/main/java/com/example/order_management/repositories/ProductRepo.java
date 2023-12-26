package com.example.order_management.repositories;
import com.example.order_management.entities.ProductItem;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public class ProductRepo {
    private final ArrayList<ProductItem> Products;

    public ProductRepo() {
        Products = new ArrayList<ProductItem>();
    }

    public void addProduct(ProductItem product){
        Products.add(product);
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
        for(ProductItem product : Products){
            if(product.getSerialNumber().equals(serialNumber)){
                Products.remove(product);
            }
        }
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
}
