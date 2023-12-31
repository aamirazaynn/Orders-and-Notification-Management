package com.example.order_management.services;

import com.example.order_management.entities.Notification;
import com.example.order_management.entities.NotificationFactory;
import com.example.order_management.repositories.NotificationRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;

@Service
public class NotificationService {
    NotificationRepo notificationRepo;

    public NotificationService(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    public void addNotification(String username, String orderId, String language, String subject, String channel) {
        Notification notification = NotificationFactory.createNotification(language, subject);
        notification.setChannel(channel);
        String template = notification.getTemplate();

        String content = replacePlaceholders(template, "{x}", username);
        content = replacePlaceholders(content, "{y}", orderId);
        notification.setContent(content);
        notificationRepo.addNotification(notification);
    }

    private String replacePlaceholders(String template, String placeholder, String replacement) {
        return template.replace(placeholder, replacement);
    }

    public LinkedList<Notification> getAllNotifications()
    {
        return notificationRepo.getAllNotifications();
    }
}
