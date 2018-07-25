package com.siemens.scifive.intelligentalarmmanagementsystem.utils;

import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.User;

public class MyStorage {
    private User user = null;
    private static MyStorage INSTANCE = null;
    private String EngID;

    public static MyStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MyStorage();
        }
        return INSTANCE;
    }

    public String getEngID() {
        return EngID;
    }

    public void setEngID(String engID) {
        EngID = engID;
    }

    public void setCurrentUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
