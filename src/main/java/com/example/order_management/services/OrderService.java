package com.example.order_management.services;

import com.example.order_management.entities.OrderComponent;
import com.example.order_management.repositories.OrderRepo;
import com.example.order_management.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
    public Boolean addOrder(OrderComponent order) {
        try {
            if(orderRepo.getOrder(order.getId()) != null){
                return false;
            }
            orderRepo.addOrder(order);
        } catch (Exception e) {
            System.out.println("Exception in addOrder as" + e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<OrderComponent> getAllOrders() {
        try {
            return orderRepo.getAllOrders();
        } catch (Exception e) {
            System.out.println("Exception in getAllOrders as" + e.getMessage());
        }
        return null;
    }
}
