package com.siemens.scifive.intelligentalarmmanagementsystem.dtos;

import java.io.Serializable;

public class Alarm{
    //alarm id	error code	eqpt id	raised date	assign date	current_status
    String AlarmId;
    String ErrorCode;
    String EquipmentId;
    String RaisedDate;
    String AssignDate;
    String CurrentStatus;

    public String getAlarmId() {
        return AlarmId;
    }

    public String getErrorCode() {
        return ErrorCode;
    }

    public String getEquipmentId() {
        return EquipmentId;
    }

    public String getRaisedDate() {
        return RaisedDate;
    }

    public String getAssignDate() {
        return AssignDate;
    }

    public String getCurrentStatus() {
        return CurrentStatus;
    }

    public Alarm(String alarmId, String errorCode, String equipmentId, String raisedDate, String assignDate, String currentStatus) {
        AlarmId = alarmId;
        ErrorCode = errorCode;
        EquipmentId = equipmentId;
        RaisedDate = raisedDate;
        AssignDate = assignDate;
        CurrentStatus = currentStatus;
    }

}
