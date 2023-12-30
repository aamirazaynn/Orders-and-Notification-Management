package com.example.order_management.entities;

public class NotificationFactory {

    public Notification createNotification(String language, String subject) {
        Notification notification = new Notification();
        if (language.equals("en")&&subject.equals("shipping")) {
            String Template = "Dear {x} , your order {y} has been shipped . thanks for using our store :) ";
            notification.setTemplate(Template);
            notification.setSubject("Shipping");
            notification.setLanguage("en");
            notification.setChannel("SMS");
        } else if(language.equals("en")&&subject.equals("placement")) {
            String Template = "Dear {x} , your order {y} has been placed. thanks for using our store :) ";
            notification.setTemplate(Template);
            notification.setSubject("Placement");
            notification.setLanguage("en");
            notification.setChannel("SMS");
        }
        else if(language.equals("ar")&&subject.equals("shipping")) {
            String Template = "عزيزى {x} ، لقد تم شحن طلبك {y} . شكرا لتسوقك معنا:)";
            notification.setTemplate(Template);
            notification.setSubject("شحن الطلب");
            notification.setLanguage("ar");
            notification.setChannel("SMS");
        }
        else if(language.equals("ar")&&subject.equals("placement")) {
            String Template = "عزيزى {x} ، لقد تم تأكيد طلبك {y} . شكرا لتسوقك معنا:)";
            notification.setTemplate(Template);
            notification.setSubject("تأكيد الطلب");
            notification.setLanguage("ar");
            notification.setChannel("SMS");
        }
        return notification;
    }
}
