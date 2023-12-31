package com.example.order_management.repositories;

import com.example.order_management.entities.CompoundOrder;
import com.example.order_management.entities.Customer;
import com.example.order_management.entities.OrderComponent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

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
    public void shipOrder(String orderID){
        for(OrderComponent order : orders){
            if (order instanceof CompoundOrder) {
                for (OrderComponent orderComponent : ((CompoundOrder) order).getOrderComponents()) {
                    orderComponent.setShipped(true);
                }
            }
            if(order.getId().equals(orderID)){
                order.setShipped(true);
            }
        }
    }
    public boolean isShipped(String orderID){
        for(OrderComponent order : orders){
            if(order.getId().equals(orderID)){
                return order.isShipped();
            }
        }
        return false;
    }
}
