package com.siemens.scifive.intelligentalarmmanagementsystem.utils;

public class MyStorage {
    private static MyStorage INSTANCE = null;

    public static MyStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MyStorage();
        }
        return INSTANCE;
    }

    private String EngID;

    public String getEngID() {
        return EngID;
    }

    public void setEngID(String engID) {
        EngID = engID;
    }
}
