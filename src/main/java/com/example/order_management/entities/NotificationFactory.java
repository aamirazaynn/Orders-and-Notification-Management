package com.example.order_management.entities;

public class NotificationFactory {

    public static Notification createNotification(String language, String subject) {
        Notification notification = new Notification();
        if (language.equals("en")&&subject.equals("shipping")) {
            String Template = "Dear {x} , your order with id ({y}) has been shipped . thanks for using our store :) ";
            notification.setTemplate(Template);
            notification.setSubject("Shipping");
            notification.setLanguage("en");
        } else if(language.equals("en")&&subject.equals("placement")) {
            String Template = "Dear {x} , your order with id ({y}) has been placed. thanks for using our store :) ";
            notification.setTemplate(Template);
            notification.setSubject("Placement");
            notification.setLanguage("en");
        }
        else if(language.equals("ar")&&subject.equals("shipping")) {
            String Template = "عزيزى {x} ، لقد تم شحن طلبك رقم ({y}) . شكرا لتسوقك معنا:)";
            notification.setTemplate(Template);
            notification.setSubject("شحن الطلب");
            notification.setLanguage("ar");
        }
        else if(language.equals("ar")&&subject.equals("placement")) {
            String Template = "عزيزى {x} ، لقد تم تأكيد طلبك رقم ({y}) . شكرا لتسوقك معنا:)";
            notification.setTemplate(Template);
            notification.setSubject("تأكيد الطلب");
            notification.setLanguage("ar");
        } else if(language.equals("en")&&subject.equals("cancel placement")) {
            String Template = "Dear {x} , your order with id ({y}) placement has been canceled. thanks for using our store :) ";
            notification.setTemplate(Template);
            notification.setSubject("Cancel Placement");
            notification.setLanguage("en");
        }
        else if(language.equals("ar")&&subject.equals("cancel placement")) {
            String Template = "عزيزى {x} ، لقد تم الغاء طلبك رقم ({y}) . شكرا لتسوقك معنا:) ";
            notification.setTemplate(Template);
            notification.setSubject("الغاء الطلب");
            notification.setLanguage("ar");
        }
        else if(language.equals("en")&&subject.equals("cancel shipping")) {
            String Template = "Dear {x} , your order with id ({y}) shipping has been canceled. thanks for using our store :) ";
            notification.setTemplate(Template);
            notification.setSubject("Cancel Shipping");
            notification.setLanguage("en");
        }
        else if(language.equals("ar")&&subject.equals("cancel shipping")) {
            String Template = "عزيزى {x} ، لقد تم الغاء شحن طلبك رقم ({y}) . شكرا لتسوقك معنا:) ";
            notification.setTemplate(Template);
            notification.setSubject("الغاء شحن الطلب");
            notification.setLanguage("ar");
        }
        return notification;
    }
}
