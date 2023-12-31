package com.example.order_management.entities;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Notification {
    String template;
    String language;
    String subject;
    String content;
    String channel;
    LocalDateTime date; // Use LocalDateTime instead of String

    public Notification(String template, String language, String subject, String content, String channel) {
        this.template = template;
        this.language = language;
        this.subject = subject;
        this.content = content;
        this.channel = channel;
        this.date = LocalDateTime.now();
    }

    public Notification() {
        this.date =LocalDateTime.now(); // Initialize date with current time
    }

    private String getCurrentTimeWithSeconds() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}