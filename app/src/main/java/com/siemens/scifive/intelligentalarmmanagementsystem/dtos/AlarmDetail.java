package com.siemens.scifive.intelligentalarmmanagementsystem.dtos;

public class AlarmDetail {
    String AlarmId;
    String EquipmentId;
    String ErrorCode;
    String CurrentStatus;
    String TroubleshootingSteps;
    String Tools;

    public String getAlarmId() {
        return AlarmId;
    }

    public void setAlarmId(String alarmId) {
        AlarmId = alarmId;
    }

    public String getEquipmentId() {
        return EquipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        EquipmentId = equipmentId;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(String errorCode) {
        ErrorCode = errorCode;
    }

    public String getCurrentStatus() {
        return CurrentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        CurrentStatus = currentStatus;
    }

    public String getTroubleshootingSteps() {
        return TroubleshootingSteps;
    }

    public void setTroubleshootingSteps(String troubleshootingSteps) {
        TroubleshootingSteps = troubleshootingSteps;
    }

    public String getTools() {
        return Tools;
    }

    public void setTools(String tools) {
        Tools = tools;
    }

    public AlarmDetail(String alarmId, String equipmentId, String errorCode, String currentStatus, String troubleshootingSteps, String tools) {

        AlarmId = alarmId;
        EquipmentId = equipmentId;
        ErrorCode = errorCode;
        CurrentStatus = currentStatus;
        TroubleshootingSteps = troubleshootingSteps;
        Tools = tools;
    }
}
