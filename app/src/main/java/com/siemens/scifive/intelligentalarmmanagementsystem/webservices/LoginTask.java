package com.siemens.scifive.intelligentalarmmanagementsystem.webservices;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.siemens.scifive.intelligentalarmmanagementsystem.interfaces.GenericWSCallback;

public class LoginTask {
    public static void fireWSCall(Context mCtx, RequestDTO requestDTO, final GenericWSCallback c) {
        c.onPreExecuteIon();

        Ion
                .with(mCtx)
                .load(WSUrls.LOGIN)                 //WILL CHANGE
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
                            ResponseDTO responseDTO = new Gson().fromJson(result, ResponseDTO.class);
                            c.onSuccess(responseDTO);
                        } catch (JsonSyntaxException e1) {
                            e1.printStackTrace();
                            c.onDataError(e1);
                        }
                    }
                });

    }

    public static class RequestDTO {
        String EngID;

        public String getEngID() {
            return EngID;
        }

        public void setEngID(String engID) {
            EngID = engID;
        }

        public RequestDTO() {
        }

        public RequestDTO(String engID) {

            EngID = engID;
        }
    }

    public static class ResponseDTO{
        String EngID;

        public ResponseDTO(String engID) {
            EngID = engID;
        }

        public ResponseDTO() {
        }

        public String getEngID() {
            return EngID;
        }

        public void setEngID(String engID) {
            EngID = engID;
        }
    }
}
