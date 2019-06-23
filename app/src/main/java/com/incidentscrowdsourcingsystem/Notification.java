package com.incidentscrowdsourcingsystem;

public class Notification {

    String title;
    String Description;
    String action;
    String actionDestination;


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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionDestination() {
        return actionDestination;
    }

    public void setActionDestination(String actionDestination) {
        this.actionDestination = actionDestination;
    }
}
