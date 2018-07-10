package com.siemens.scifive.intelligentalarmmanagementsystem.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.siemens.scifive.intelligentalarmmanagementsystem.R;

public class LoginActivity extends AppCompatActivity {
    EditText etLAEngineerID;
    EditText etLAPassword;
    Button bLALogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLAEngineerID = findViewById(R.id.etLAEngineerID);
        etLAPassword = findViewById(R.id.etLAPassword);
        bLALogin = findViewById(R.id.bLALogin);

        bLALogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //HERE LOGIN WEBSERVICE WILL BE CALLED
                //ON ITS RESPONSE WE WILL HAVE TO CHECK WEATHER THE USER IS ADMIN OR USER
                //AND ACCORDINGLY WE WILL SWITCH THE WINDOW (ADMIN OR USER)

                //MEANWHILE WE WILL CONSIDER THAT 'A USER' HAS LOGGED IN
                loginUser();
            }
        });

    }

    private void loginUser() {
        Intent i=new Intent(LoginActivity.this,UserHomeActivity.class);
        startActivity(i);
    }
}
