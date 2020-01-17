package com.example.e_regulator;

public class Device {
    private String id;
    private int priority;
    private String description;
    private String category;
    private int icon;

    public Device(String id,int priority, String description, String category, int icon) {
        this.id = id;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
