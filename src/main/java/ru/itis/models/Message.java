package ru.itis.models;

import java.sql.Timestamp;

public class Message {

    private int userId;
    private String title;
    private String text;
    private Timestamp timeOfCreate;

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimeOfCreate() {
        return timeOfCreate;
    }

    public void setTimeOfCreate(Timestamp timeOfCreate) {
        this.timeOfCreate = timeOfCreate;
    }
}
