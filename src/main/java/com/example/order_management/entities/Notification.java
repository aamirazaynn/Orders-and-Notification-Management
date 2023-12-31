package com.example.order_management.entities;

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

    public Notification(String template, String language, String subject, String content, String channel) {
        this.template = template;
        this.language = language;
        this.subject = subject;
        this.content = content;
        this.channel = channel;
    }

    public Notification() {}

}
