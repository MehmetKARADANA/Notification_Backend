package com.mehmetkaradana.notification;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        InputStream serviceAccount = getClass().getClassLoader()
                .getResourceAsStream("tskpersonelteminapp-firebase-adminsdk.json");
                //new FileInputStream("C:\\Users\\Mehmet\\Desktop\\test\\notification\\notification\\src\\main\\resources\\tskpersonelteminapp-firebase-adminsdk.json");

        if (serviceAccount == null) {
            throw new IOException("Firebase config file not found in resources folder!");
        }

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();


        return FirebaseApp.initializeApp(options);
    }
}
