package com.example.order_management.services;

import com.example.order_management.entities.Customer;
import com.example.order_management.entities.OrderComponent;
import com.example.order_management.repositories.OrderRepo;
import com.example.order_management.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }
    public Boolean addOrder(OrderComponent order) {
        try {
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
    public OrderComponent getOrder(String orderID){
        try {
            return orderRepo.getOrder(orderID);
        } catch (Exception e) {
            System.out.println("Exception in getOrder as" + e.getMessage());
        }
        return null;
    }
    public void shipOrder(String orderID){
        try {
            orderRepo.shipOrder(orderID);
        } catch (Exception e) {
            System.out.println("Exception in shipOrder as" + e.getMessage());
        }
    }
    public boolean isShipped(String orderID){
        try {
            return orderRepo.isShipped(orderID);
        } catch (Exception e) {
            System.out.println("Exception in isShipped as" + e.getMessage());
        }
        return false;
    }
    public void removeOrder(String orderID){
        try {
            orderRepo.removeOrder(orderID);
        } catch (Exception e) {
            System.out.println("Exception in removeOrder as" + e.getMessage());
        }
    }
}
