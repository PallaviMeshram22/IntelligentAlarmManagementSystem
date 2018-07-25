package com.siemens.scifive.intelligentalarmmanagementsystem.dtos;

public class User {
    int UserId;
    String EngineerId;
    String Password;

    public User() {

    }

    public User(int userId, String engineerId, String password) {

        UserId = userId;
        EngineerId = engineerId;
        Password = password;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getEngineerId() {
        return EngineerId;
    }

    public void setEngineerId(String engineerId) {
        EngineerId = engineerId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
