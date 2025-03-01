# Hafif ve optimize edilmiş bir OpenJDK 21 imajı kullanıyoruz
FROM eclipse-temurin:21-jre

# Çalışma dizinini ayarla
WORKDIR /app

# Maven ile derlenen JAR dosyasını container içine kopyala
COPY target/notification-0.0.1-SNAPSHOT.jar application.jar

# Uygulamanın çalıştırılacağı komutu belirtiyoruz
ENTRYPOINT ["java", "-jar", "application.jar"]

# Spring Boot'un çalışacağı portu açıyoruz
EXPOSE 8080