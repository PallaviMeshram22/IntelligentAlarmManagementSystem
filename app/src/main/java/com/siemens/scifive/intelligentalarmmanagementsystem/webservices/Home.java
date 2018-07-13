package com.siemens.scifive.intelligentalarmmanagementsystem.webservices;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.Alarm;
import com.siemens.scifive.intelligentalarmmanagementsystem.interfaces.GenericWSCallback;

import java.util.List;

import static java.lang.System.load;

public class Home {


    public static void fireWSCall(Context mCtx, RequestDTO requestDTO, final GenericWSCallback c) {
        c.onPreExecuteIon();

        Ion
                .with(mCtx)
                .load(WSUrls.HOME)                 //WILL CHANGE
                .setJsonPojoBody(requestDTO)
                //.setStringBody(new Gson().toJson(requestDTO))
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        c.onIonComplete();
                        if (e!=null){
                            e.printStackTrace();
                            c.onNetworkError(e);
                            return;
                        }
                        try {
                            LoginTask.ResponseDTO responseDTO = new Gson().fromJson(result, LoginTask.ResponseDTO.class);
                            c.onSuccess(responseDTO);
                        } catch (JsonSyntaxException e1) {
                            e1.printStackTrace();
                            c.onDataError(e1);
                        }
                    }
                });

    }


    public static class RequestDTO
    {
        String EngID;

        public RequestDTO(String engID) {
            EngID = engID;
        }

        public String getEngID() {
            return EngID;
        }

        public void setEngID(String engID) {
            EngID = engID;
        }

        public RequestDTO() {
        }

        /*public RequestDTO(String engineerId) {
            this.engineerId = engineerId;
        }

        public RequestDTO() {
        }

        public String getEngineerId() {
            return engineerId;
        }

        public void setEngineerId(String engineerId) {
            this.engineerId = engineerId;
        }*/
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
