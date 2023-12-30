package com.example.order_management.repositories;

import com.example.order_management.entities.Notification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.Queue;

@Repository
@Component
public class NotificationRepo {
    private final Queue<Notification> notifications;

    public NotificationRepo(Queue<Notification> notifications) {
        this.notifications = notifications;
    }

    public void addNotification(Notification notification){
        notifications.add(notification);
    }

    public Queue<Notification> getAllNotifications(){
        return notifications;
    }



}
