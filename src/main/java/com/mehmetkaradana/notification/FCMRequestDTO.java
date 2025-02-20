package com.mehmetkaradana.notification;



public class FCMRequestDTO {

    private final String to;
    private final String title;
    private final String body;


   public FCMRequestDTO(String to, String title, String body){
        this.to=to;
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getTo() {
        return to;
    }

}
