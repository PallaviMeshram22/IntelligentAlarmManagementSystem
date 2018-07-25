package com.siemens.scifive.intelligentalarmmanagementsystem.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.siemens.scifive.intelligentalarmmanagementsystem.Manifest;
import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.User;
import com.siemens.scifive.intelligentalarmmanagementsystem.interfaces.GenericWSCallback;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.Constants;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.MyAlertDialog;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.MyProgressDialog;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.MyStorage;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.Validation;
import com.siemens.scifive.intelligentalarmmanagementsystem.webservices.LoginTask;

public class LoginActivity extends AppCompatActivity {
    EditText etLAEngineerID;
    EditText etLAPassword;
    Button bLALogin;

    Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCtx=this;

        etLAEngineerID = findViewById(R.id.etLAEngineerID);
        etLAPassword = findViewById(R.id.etLAPassword);
        bLALogin = findViewById(R.id.bLALogin);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
            }, 021);
        }else{
            MAIN();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i=0; i<permissions.length;i++){
            if (grantResults[i]== PackageManager.PERMISSION_GRANTED){

            }else{
                Toast.makeText(mCtx, "Please allow all permissions", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        MAIN();
    }

    private void MAIN() {

        bLALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //HERE LOGIN WEBSERVICE WILL BE CALLED
                //ON ITS RESPONSE WE WILL HAVE TO CHECK WEATHER THE USER IS ADMIN OR USER
                //AND ACCORDINGLY WE WILL SWITCH THE WINDOW (ADMIN OR USER)

                //MEANWHILE WE WILL CONSIDER THAT 'A USER' HAS LOGGED IN
                //loginUser();

                //STEP 1 : create string attributes
                final String EngID = etLAEngineerID.getText().toString().trim();
                String password = etLAPassword.getText().toString().trim();

                User user = Constants.getUser(EngID, password);
                if (user==null){
                    Toast.makeText(mCtx, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("ShraX", "user = "+user.getUserId());
                MyStorage.getInstance().setCurrentUser(user);
                loginUser();

                //step 2: call webservice
                //<editor-fold desc="WEBSERVICE CALL">
                /*LoginTask.fireWSCall(mCtx, new LoginTask.RequestDTO(EngID), new GenericWSCallback() {
                    @Override
                    public void onPreExecuteIon() {
                        MyProgressDialog.showPleaseWait(mCtx);
                    }

                    @Override
                    public void onIonComplete() {
                        MyProgressDialog.dismiss();
                    }

                    @Override
                    public void onNetworkError(Exception e) {
                        MyAlertDialog.showNetworkErrorDialog(mCtx);
                    }

                    @Override
                    public void onDataError(Exception e) {
                        MyAlertDialog.showDataErrorDialog(mCtx, e);

                    }

                    @Override
                    public void onSuccess(Object o) {
                        LoginTask.ResponseDTO responseDTO = (LoginTask.ResponseDTO) o;

                        MyStorage.getInstance().setEngID(responseDTO.getEngId());

                        if (responseDTO.getEngId().equals(EngID))
                        {
                            //OPEN ADMIN ACTIVITY
                            Intent i = new Intent(LoginActivity.this, UserHomeActivity.class);
                            startActivity(i);
                        }else {
                            //OPEN USER ACTIVITY
                            //Intent i = new Intent(LoginActivity.this, UserHomeActivity.class);
                            //startActivity(i);
                        }

                    }
                });*/
                //</editor-fold>




            }
        });

    }

    private void loginUser() {
        Intent i = new Intent(LoginActivity.this, UserHomeActivity.class);
        startActivity(i);
        finish();
    }
}
