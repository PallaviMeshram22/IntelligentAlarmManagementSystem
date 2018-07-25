package com.siemens.scifive.intelligentalarmmanagementsystem.dtos;

import java.io.Serializable;


public class Alarm{
    //alarm id	error code	eqpt id	raised date	assign date	current_status
    String EntryID;
    String ErrorCode;
    String EquipID;
    String RaisedDate;
    String AssignedDate;
    String EngID;
    String CurrentStatus;

    boolean resolved;

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    //for alarm details
    String TroubleshootingStepsCompany;
    String ToolsCompany;

    //for resolved activity input
    boolean existingStepsHelpful;
    String existingStepsHelpfulComment;
    boolean existingToolsHelpful;
    String existingToolsHelpfulComment;

    //for location fragment
    double alarmLocationLatitude;
    double alarmLocationLongitude;

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

    public String getAssignedDate() {
        return AssignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        AssignedDate = assignedDate;
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

    public String getTroubleshootingStepsCompany() {
        return TroubleshootingStepsCompany;
    }

    public void setTroubleshootingStepsCompany(String troubleshootingStepsCompany) {
        TroubleshootingStepsCompany = troubleshootingStepsCompany;
    }

    public String getToolsCompany() {
        return ToolsCompany;
    }

    public void setToolsCompany(String toolsCompany) {
        ToolsCompany = toolsCompany;
    }

    public boolean isExistingStepsHelpful() {
        return existingStepsHelpful;
    }

    public void setExistingStepsHelpful(boolean existingStepsHelpful) {
        this.existingStepsHelpful = existingStepsHelpful;
    }

    public String getExistingStepsHelpfulComment() {
        return existingStepsHelpfulComment;
    }

    public void setExistingStepsHelpfulComment(String existingStepsHelpfulComment) {
        this.existingStepsHelpfulComment = existingStepsHelpfulComment;
    }

    public boolean isExistingToolsHelpful() {
        return existingToolsHelpful;
    }

    public void setExistingToolsHelpful(boolean existingToolsHelpful) {
        this.existingToolsHelpful = existingToolsHelpful;
    }

    public String getExistingToolsHelpfulComment() {
        return existingToolsHelpfulComment;
    }

    public void setExistingToolsHelpfulComment(String existingToolsHelpfulComment) {
        this.existingToolsHelpfulComment = existingToolsHelpfulComment;
    }

    public double getAlarmLocationLatitude() {
        return alarmLocationLatitude;
    }

    public void setAlarmLocationLatitude(double alarmLocationLatitude) {
        this.alarmLocationLatitude = alarmLocationLatitude;
    }

    public double getAlarmLocationLongitude() {
        return alarmLocationLongitude;
    }

    public void setAlarmLocationLongitude(double alarmLocationLongitude) {
        this.alarmLocationLongitude = alarmLocationLongitude;
    }

    public Alarm(String entryID, String errorCode, String equipID, String raisedDate, String assignedDate, String engID, String currentStatus, String troubleshootingStepsCompany, String toolsCompany, boolean existingStepsHelpful, String existingStepsHelpfulComment, boolean existingToolsHelpful, String existingToolsHelpfulComment, double alarmLocationLatitude, double alarmLocationLongitude) {
        EntryID = entryID;
        ErrorCode = errorCode;
        EquipID = equipID;
        RaisedDate = raisedDate;
        AssignedDate = assignedDate;
        EngID = engID;
        CurrentStatus = currentStatus;
        TroubleshootingStepsCompany = troubleshootingStepsCompany;
        ToolsCompany = toolsCompany;
        this.existingStepsHelpful = existingStepsHelpful;
        this.existingStepsHelpfulComment = existingStepsHelpfulComment;
        this.existingToolsHelpful = existingToolsHelpful;
        this.existingToolsHelpfulComment = existingToolsHelpfulComment;
        this.alarmLocationLatitude = alarmLocationLatitude;
        this.alarmLocationLongitude = alarmLocationLongitude;
        resolved=false;
    }

    public Alarm() {
        resolved=false;
    }
}

