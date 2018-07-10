package com.siemens.scifive.intelligentalarmmanagementsystem.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.Alarm;

import static com.siemens.scifive.intelligentalarmmanagementsystem.fragments.UserHomeFragment.SELECTED_ALARM_JSON_INTENT_EXTRA;

public class AlarmDetailsActivity extends AppCompatActivity {
    TextView tvAADAlarmDescription;
    Button bAADAccept;
    Button bAADCancle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_details);

        tvAADAlarmDescription = findViewById(R.id.tvAADAlarmDescription);
        bAADAccept = findViewById(R.id.bAADAccept);
        bAADCancle = findViewById(R.id.bAADCancle);

        String selectedAlarmJSON = getIntent().getStringExtra(SELECTED_ALARM_JSON_INTENT_EXTRA);
        Alarm selectedAlarm = new Gson().fromJson(selectedAlarmJSON, Alarm.class);

        StringBuilder s = new StringBuilder();
        s.append("Short description of alarm id : ").append(selectedAlarm.getAlarmId()).append("\n");
        s.append("Equipment id : ").append(selectedAlarm.getEquipmentId()).append("\n");
        s.append("Error Type : ").append(selectedAlarm.getErrorCode()).append("\n");

        tvAADAlarmDescription.setText(s.toString());



        bAADCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
