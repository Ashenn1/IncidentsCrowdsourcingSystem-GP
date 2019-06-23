package com.incidentscrowdsourcingsystem;

public class UserData
{

    String Name;
    int id;
    String Email;

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
