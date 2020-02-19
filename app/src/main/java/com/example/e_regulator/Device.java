package com.example.e_regulator;

public class Device {
    public  String id;
    public String userId;
    public int priority;
    public String description;
    public String category;

    public Device(){}

    public Device(String id,String userId, int priority, String description, String category) {
        this.id = id;
        this.userId = userId;
        this.priority = priority;
        this.description = description;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
