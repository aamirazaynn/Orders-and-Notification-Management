package com.example.order_management.controllers;

import com.example.order_management.entities.Notification;
import com.example.order_management.services.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/getAllNotifications")
    public ArrayList<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @GetMapping("/getMostUsedChannel")
    public String getMostUsedChannel(){
        return notificationService.getMostUsedChannel();
    }

    @GetMapping("/getMostUsedTemplate")
    public String getMostUsedTemplate(){
        return notificationService.getMostUsedTemplate();
    }
}
