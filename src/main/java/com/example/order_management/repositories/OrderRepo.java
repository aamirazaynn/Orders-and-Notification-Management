package com.example.order_management.repositories;

import com.example.order_management.entities.OrderComponent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
@Component
public class OrderRepo {
    private final ArrayList<OrderComponent> orders;

    public OrderRepo(ArrayList<OrderComponent> orders) {
        this.orders = orders;
    }

    public OrderComponent getOrder(String orderID){
        for(OrderComponent order : orders){
            if(order.getId().equals(orderID)){
                return order;
            }
        }
        return null;
    }
    public ArrayList<OrderComponent> getAllOrders(){
        return orders;
    }
    public void addOrder(OrderComponent order){
        orders.add(order);
        System.out.println(this.orders);
    }
    public void removeOrder(String orderID){
        orders.removeIf(product -> product.getId().equals(orderID));
    }
}
