package com.siemens.scifive.intelligentalarmmanagementsystem.utils;

public class MyStorage {
    private static MyStorage INSTANCE = null;

    public static MyStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MyStorage();
        }
        return INSTANCE;
    }

    private String EngineerID;

    public String getEngineerID() {
        return EngineerID;
    }

    public void setEngineerID(String engineerID) {
        EngineerID = engineerID;
    }
}
