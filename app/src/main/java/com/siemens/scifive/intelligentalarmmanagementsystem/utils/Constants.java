package com.siemens.scifive.intelligentalarmmanagementsystem.utils;

import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.Alarm;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.User;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static List<Alarm> alarms = null;
    public static List<Alarm> resolvedAlarms = null;

    public static List<User> getUsersList() {


        List<User> users = new ArrayList<>();
        users.add(new User(01, "EN01", "Test123$"));
        users.add(new User(02, "EN02", "Test123$"));

        return users;
    }

    public static List<Alarm> getFreshResolvedAlarmsList() {
        List<Alarm> alarms = new ArrayList<>();
        return alarms;
    }

    public static List<Alarm> getFreshAlarmsList(User user) {
        List<Alarm> alarms = new ArrayList<>();
        if (user.getUserId() == 01) {
            alarms.add(new Alarm("3", "ER003", "EQ03", "2018-07-10", " ", "", "Open", "Check the Power Supply\nCheck the communctaion\nReset the Equipment Settings", "Multimeter\nheat resistant gloves", false, "", false, "", 18.556653, 73.793521));
            alarms.add(new Alarm("4", "ER004", "EQ05", "2018-07-10", " ", "", "Open", "Check the Power Supply\nCheck the PLC connection\nCheck the manual mode", "PLC testing kit\nvoltage tester\nWire Strippers", false, "", false, "", 18.556653, 73.793521));
            alarms.add(new Alarm("7", "ER007", "EQ03", "2018-07-10", " ", "", "Open", "Check the Power Supply\nCheck the PLC connection\nCheck the Auto mode", "Wire\nPLC testing kit\nHammer", false, "", false, "", 18.556653, 73.793521));
        } else if (user.getUserId() == 02) {
            alarms.add(new Alarm("10", "ER010", "EQ99", "2018-07-10", " ", "", "Open", "Check the Power Supply\nCheck the PLC connection\nCheck the manual mode", "Wire\nPLC testing kit\nHammer", false, "", false, "", 18.556653, 73.793521));
            alarms.add(new Alarm("12", "ER012", "EQ92", "2018-07-10", " ", "", "Open", "Check the Power Supply\nCheck the communctaion\nReset the Equipment Settings", "Multimeter\nheat resistant gloves", false, "", false, "", 18.556653, 73.793521));
            alarms.add(new Alarm("14", "ER014", "EQ45", "2018-07-10", " ", "", "Open", "Check the Power Supply\nCheck the PLC connection\nCheck the Auto mode", "PLC testing kit\nvoltage tester\nWire Strippers", false, "", false, "", 18.556653, 73.793521));
        }
        return alarms;
    }


    public static List<Alarm> getResolvedAlarmsList() {
        if (resolvedAlarms==null){
            resolvedAlarms=getFreshResolvedAlarmsList();
        }
        return resolvedAlarms;
    }
    public static List<Alarm> getAlarmsList(User user) {
        if (alarms == null) {
            alarms = getFreshAlarmsList(user);
        }
        return alarms;
    }

    public static User getUser(String engID, String password) {
        User user = null;
        for (User u : getUsersList()) {
            if (u.getEngineerId().equals(engID)) {
                user = u;
            }
        }
        return user;
    }

    public static void d(String TAG, String message) {
        int maxLogSize = 2000;
        for (int i = 0; i <= message.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > message.length() ? message.length() : end;
            android.util.Log.d(TAG, message.substring(start, end));
        }
    }
}
