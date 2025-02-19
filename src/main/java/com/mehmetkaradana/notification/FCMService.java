package com.mehmetkaradana.notification;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FCMService {
    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public FCMService(FirebaseApp firebaseApp) {
        this.firebaseMessaging = FirebaseMessaging.getInstance(firebaseApp);
    }

    public String sendMessage(FCMRequestDTO requestDTO) throws FirebaseMessagingException {

        Notification notification = Notification.builder()
                .setTitle(requestDTO.getNotificationBody().getTitle())
                .setBody(requestDTO.getNotificationBody().getBody())// "chat" konusuna abone olan herkese gider
                .build();

        Map<String, String> data = new HashMap<>();
        data.put("title", "test title");
        data.put("body", "test notification");

        Message message = Message.builder().setNotification(notification)
                .putAllData(data)
                .setToken(requestDTO.getTo())
                .build();
        System.out.println("[sendMessage] : message :"+message.toString());
        return firebaseMessaging.send(message);

    }

    public String broadcast(FCMRequestDTO requestDTO) throws FirebaseMessagingException {

        Notification notification = Notification.builder()
                        .setTitle(requestDTO.getNotificationBody().getTitle())
                        .setBody(requestDTO.getNotificationBody().getBody())// "chat" konusuna abone olan herkese gider
                .build();
        Map<String, String> data = new HashMap<>();
        data.put("title", requestDTO.getNotificationBody().getTitle());
        data.put("body", requestDTO.getNotificationBody().getBody());
        Message message = Message.builder()
                .setNotification(notification)
                .putAllData(data)
                .setTopic("chat")
                .build();
        System.out.println("[broadcast] : message :"+message.toString());

        return firebaseMessaging.send(message);

    }
}
