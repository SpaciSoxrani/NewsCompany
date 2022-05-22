package com.example.hostapp.mails;

public class MailingItem {
    public String name;
    public int image;
    public String prediction;
    public Double score;
    public String dateTime;

    public String getId() {
        return id;
    }

    private final String id;

    public MailingItem(int image, String name, String prediction, Double score, String dateTime, String id) {
        this.name = name;
        this.image = image;
        this.prediction = prediction;
        this.score = score;
        this.dateTime = dateTime;
        this.id=id;
    }
}
