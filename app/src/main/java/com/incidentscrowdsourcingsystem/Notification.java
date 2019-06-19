package com.incidentscrowdsourcingsystem;

public class Notification {

    String title;
    String Description;

    public Notification(String title, String Description) {
        this.title = title;
        this.Description = Description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
