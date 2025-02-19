package com.mehmetkaradana.notification;

import java.security.PublicKey;


public class FCMRequestDTO {

    private String to;
    private NotificationBody notificationBody= new NotificationBody();

    public NotificationBody getNotificationBody() {
        return notificationBody;
    }

    public void setNotificationBody(NotificationBody notificationBody) {
        this.notificationBody = notificationBody;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public String getTo() {
        return to;
    }

}
