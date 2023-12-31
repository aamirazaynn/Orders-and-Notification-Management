package com.example.order_management.repositories;

import com.example.order_management.entities.Notification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
@Component
public class NotificationRepo {
    private final ArrayList<Notification> notifications;
    private final Map<String, Integer> channelStatistics;
    private final Map<String, Integer> templateStatistics;

    public NotificationRepo(ArrayList<Notification> notifications, Map<String, Integer> stat, Map<String, Integer> templateStatistics) {
        this.notifications = notifications;
        channelStatistics = stat;
        this.templateStatistics = templateStatistics;
    }
    public void addNotification(Notification notification){
        notifications.add(notification);
    }
    public void removeNotification(Notification notification){
        notifications.remove(notification);
    }
    public ArrayList<Notification> getAllNotifications(){
        return notifications;
    }
    public String getMostUsedChannel(){
        int max = 0;
        String mostUsedChannel = "";
        for (Map.Entry<String, Integer> entry : channelStatistics.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mostUsedChannel = entry.getKey();
            }
        }
        return mostUsedChannel;
    }
    public String getMostUsedTemplate(){
        int max = 0;
        String mostUsedTemplate = "";
        for (Map.Entry<String, Integer> entry : templateStatistics.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                mostUsedTemplate = entry.getKey();
            }
        }
        return mostUsedTemplate;
    }
    public void updateTemplateStat(String key){
        if (templateStatistics.containsKey(key)) {
            // If found, increment the value by 1
            int currentValue = templateStatistics.get(key);
            templateStatistics.put(key, currentValue + 1);
        } else {
            // If not found, set the value to 1
            templateStatistics.put(key, 1);
        }
    }
    public void updateChannelStat(String key){
        if (channelStatistics.containsKey(key)) {
            // If found, increment the value by 1
            int currentValue = channelStatistics.get(key);
            channelStatistics.put(key, currentValue + 1);
        } else {
            // If not found, set the value to 1
            channelStatistics.put(key, 1);
        }
    }
    public void print () {
        System.out.println("Channel Statistics:");
        for (Map.Entry<String, Integer> entry : channelStatistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Template Statistics:");
        for (Map.Entry<String, Integer> entry : templateStatistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}