package com.siemens.scifive.intelligentalarmmanagementsystem.dtos;

import java.io.Serializable;

public class Alarm{
    //alarm id	error code	eqpt id	raised date	assign date	current_status
    String EntryID;
    String ErrorCode;
    String EquipID;
    String RaisedDate;
    String EngID;
    String CurrentStatus;

    public String getEntryID() {
        return EntryID;
    }

    public void setEntryID(String entryID) {
        EntryID = entryID;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getEquipID() {
        return EquipID;
    }

    public void setEquipID(String equipID) {
        EquipID = equipID;
    }

    public String getRaisedDate() {
        return RaisedDate;
    }

    public void setRaisedDate(String raisedDate) {
        RaisedDate = raisedDate;
    }

    public String getEngID() {
        return EngID;
    }

    public void setEngID(String engID) {
        EngID = engID;
    }

    public String getCurrentStatus() {
        return CurrentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        CurrentStatus = currentStatus;
    }

    public Alarm(String entryID, String errorCode, String equipID, String raisedDate, String engID, String currentStatus) {
        EntryID = entryID;
        ErrorCode = errorCode;
        EquipID = equipID;
        RaisedDate = raisedDate;
        EngID = engID;
        CurrentStatus = currentStatus;
    }


    /* public String getAlarmId() {
        return EntryID;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public String getEquipmentId() {
        return EquipID;
    }

    public String getRaisedDate() {
        return RaisedDate;
    }

    public String getAssignDate() {
        return EngID;
    }

    public String getCurrentStatus() {
        return CurrentStatus;
    }

    public Alarm(String entryID, String errorCode, String equipID, String raisedDate, String engID, String currentStatus) {
        EntryID = entryID;
        ErrorCode = errorCode;
        EquipID = equipID;
        RaisedDate = raisedDate;
        EngID = engID;
        CurrentStatus = currentStatus;
    }*/
}
