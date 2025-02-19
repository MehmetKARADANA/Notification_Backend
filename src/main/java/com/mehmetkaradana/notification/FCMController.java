package com.mehmetkaradana.notification;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fcm")
public class FCMController {

    private final FCMService fcmService;

    @Autowired
    public FCMController(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody FCMRequestDTO requestDTO) {

        try {

            return ResponseEntity.ok(fcmService.sendMessage(requestDTO));
        } catch (FirebaseMessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.ok("FCM message sent successfully.");
    }

    @PostMapping("/broadcast")
    public ResponseEntity<String> broadcast(@RequestBody FCMRequestDTO requestDTO) {

        try {

            return ResponseEntity.ok(fcmService.broadcast(requestDTO));
        } catch (FirebaseMessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseEntity.ok("FCM message sent successfully.");
    }
}