package com.example.e_regulator;

public class Device {
    public  String id;
    public String userId;
    public int priority;
    public String description;
    public String category;
    public int icon;

    public Device(){}

    public Device(String id,String userId, int priority, String description, String category, int icon) {
        this.id = id;
        this.userId = userId;
        this.priority = priority;
        this.description = description;
        this.category = category;
        this.icon = icon;
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

    public int getIcon() { return icon; }

    public void setIcon(int icon) { this.icon = icon; }

}
