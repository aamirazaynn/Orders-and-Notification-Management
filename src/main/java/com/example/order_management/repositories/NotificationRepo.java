package com.example.order_management.repositories;

import com.example.order_management.entities.Notification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
@Component
public class NotificationRepo {
    private final LinkedList<Notification> notifications;

    public NotificationRepo(LinkedList<Notification> notifications) {
        this.notifications = notifications;
    }
    public void addNotification(Notification notification){
        notifications.add(notification);
    }
    public LinkedList<Notification> getAllNotifications(){
        return notifications;
    }

}