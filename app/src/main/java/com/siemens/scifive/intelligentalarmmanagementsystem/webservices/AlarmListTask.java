package com.siemens.scifive.intelligentalarmmanagementsystem.webservices;

import android.content.Context;

import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.Alarm;
import com.siemens.scifive.intelligentalarmmanagementsystem.interfaces.GenericWSCallback;

import java.util.List;

public class AlarmListTask {


    public static void fireWSCall(Context mCtx, RequestDTO requestDTO, GenericWSCallback c) {

    }


    public static class RequestDTO
    {
        String engineerId;

        public RequestDTO(String engineerId) {
            this.engineerId = engineerId;
        }

        public RequestDTO() {
        }

        public String getEngineerId() {
            return engineerId;
        }

        public void setEngineerId(String engineerId) {
            this.engineerId = engineerId;
        }
    }

    public static class ResponseDTO
    {
        List<Alarm> alarms;

        public ResponseDTO(List<Alarm> alarms) {
            this.alarms = alarms;
        }

        public ResponseDTO() {
        }

        public List<Alarm> getAlarms() {
            return alarms;
        }

        public void setAlarms(List<Alarm> alarms) {
            this.alarms = alarms;
        }
    }
}
