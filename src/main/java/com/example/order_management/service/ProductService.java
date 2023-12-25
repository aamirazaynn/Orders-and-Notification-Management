package com.example.order_management.service;
import com.example.order_management.entities.ProductItem;
import com.example.order_management.Common;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductService {

    public Boolean addProduct(ProductItem product) {
        try {
            if(Common.ListOfProducts.get(product.getSerialNumber()) != null){
                return false;
            }
            Common.ListOfProducts.put(product.getSerialNumber(), product);
        } catch (Exception e) {
            System.out.println("Exception in addProduct as" + e.getMessage());
            return false;
        }
        return true;
    }

    public ProductItem[] getAllPersons() {
        try {
            Set<String> serialNums = Common.ListOfProducts.keySet();
            ProductItem[] p = new ProductItem[serialNums.size()];
            int i = 0;
            for(String serialNum : serialNums){
                p[i] = Common.ListOfProducts.get(serialNum);
                i++;
            }
            return p;
        } catch (Exception e) {
            System.out.println("Exception in getAllProducts as" + e.getMessage());
        }
        return null;
    }
}
