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

        if (requestDTO.getTitle() == null || requestDTO.getBody() == null || requestDTO.getTo() == null || requestDTO.getTo().isEmpty()) {
            throw new IllegalArgumentException("Title, body ve to alanları boş olamaz.");
        }
        Notification notification = Notification.builder()
                .setTitle(requestDTO.getTitle())
                .setBody(requestDTO.getBody())// "chat" konusuna abone olan herkese gider
                .build();

        Map<String, String> data = new HashMap<>();
        data.put("title", requestDTO.getTitle());
        data.put("body", requestDTO.getBody());

        Message message = Message.builder().setNotification(notification)
                .putAllData(data)
                .setToken(requestDTO.getTo())
                .build();
        System.out.println("[sendMessage] : message :"+message.toString());
        return firebaseMessaging.send(message);

    }

    public String broadcast(FCMRequestDTO requestDTO) throws FirebaseMessagingException {
        if (requestDTO.getTitle() == null || requestDTO.getBody() == null ) {
            throw new IllegalArgumentException("Title, body alanları boş olamaz.");
        }
        Notification notification = Notification.builder()
                        .setTitle(requestDTO.getTitle())
                        .setBody(requestDTO.getBody())// "chat" konusuna abone olan herkese gider
                .build();
        Map<String, String> data = new HashMap<>();
        data.put("title", requestDTO.getTitle());
        data.put("body", requestDTO.getBody());
        Message message = Message.builder()
               // .setNotification(notification)
                .putAllData(data)
                .setTopic("chat")
                .build();
        System.out.println("[broadcast] : message :"+message.toString());

        return firebaseMessaging.send(message);

    }
}
