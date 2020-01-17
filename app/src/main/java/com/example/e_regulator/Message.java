package com.example.e_regulator;

import java.util.Date;

public class Message {
    private int id;
    private String content;
    private Date sendTime;
    private User sender;

    public Message(int id, String content, Date sendTime, User sender) {
        this.id = id;
        this.content = content;
        this.sendTime = sendTime;
        this.sender = sender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
