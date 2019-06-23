package com.incidentscrowdsourcingsystem;

import java.util.Date;

public class UserData
{

    private String Name;
    private int id;
    private String Email;
    private Date sessionExpiryDate;

    public void setSessionExpiryDate(Date sessionExpiryDate) {
        this.sessionExpiryDate = sessionExpiryDate;
    }


    public Date getSessionExpiryDate() {
        return sessionExpiryDate;
    }


    public UserData() {
    }

    public UserData(int id,String name, String email) {
        Name = name;
        this.id = id;
        Email = email;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
