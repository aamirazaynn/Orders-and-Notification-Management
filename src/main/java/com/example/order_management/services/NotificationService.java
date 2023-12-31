package com.example.order_management.services;

import java.time.Duration;
import java.time.LocalDateTime;
import com.example.order_management.entities.Customer;
import com.example.order_management.entities.Notification;
import com.example.order_management.entities.NotificationFactory;
import com.example.order_management.repositories.NotificationRepo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class NotificationService {
    NotificationRepo notificationRepo;

    public NotificationService(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }
    public void addNotification(Customer customer, String orderId, String subject) {
        String language = customer.getLanguage();
        String channel = customer.getChannel();
        String username = customer.getUsername();

        // Create a new notification
        Notification notification = NotificationFactory.createNotification(language, subject);
        notification.setChannel(channel);

        // Replace placeholders in the template
        String template = notification.getTemplate();
        String content = replacePlaceholders(template, "{x}", username);
        content = replacePlaceholders(content, "{y}", orderId);
        notification.setContent(content);

        // Add the notification to the repository
        notificationRepo.addNotification(notification);

        // update channel statistics
        if (channel.equals("sms")) {
            String phone = customer.getPhoneNumber();
            notificationRepo.updateChannelStat(phone);
        } else if(channel.equals("email")) {
            String email = customer.getEmail();
            notificationRepo.updateChannelStat(email);
        }

        // update template statistics
        notificationRepo.updateTemplateStat(template);
    }
    private String replacePlaceholders(String template, String placeholder, String replacement) {
        return template.replace(placeholder, replacement);
    }
    public ArrayList<Notification> getAllNotifications() {
        removeNotification();
        return notificationRepo.getAllNotifications();
    }
    public void removeNotification(){
        ArrayList<Notification> notifications = notificationRepo.getAllNotifications();
        for (Notification n : notifications) {
            if (isNotificationExpired(n)) {
                notificationRepo.removeNotification(n);
            }
        }
    }
    private boolean isNotificationExpired(Notification notification) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oldDate = notification.getDate();
        // Calculate the duration between old date and now
        Duration duration = Duration.between(oldDate, now);

        // Check if the duration is exactly 30 seconds
        boolean is30SecondsDifference = duration.getSeconds() >= 30;
        return is30SecondsDifference;
    }
    public String getMostUsedChannel(){
        return notificationRepo.getMostUsedChannel();
    }
    public String getMostUsedTemplate(){
        return notificationRepo.getMostUsedTemplate();
    }
}
