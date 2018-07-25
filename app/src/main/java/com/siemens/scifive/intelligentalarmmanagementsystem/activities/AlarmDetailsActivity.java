package com.siemens.scifive.intelligentalarmmanagementsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.Alarm;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.User;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.siemens.scifive.intelligentalarmmanagementsystem.fragments.UserHomeFragment.SELECTED_ALARM_JSON_INTENT_EXTRA;

public class AlarmDetailsActivity extends AppCompatActivity {
    public static final String ALARM_DETAILS_ACTIVITY_RETURNING_DATA = "adard";
    TextView tvAADAlarmDescription;
    Button bAADAccept;
    Button bAADCancle;
    Alarm selectedAlarm;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_details);

        tvAADAlarmDescription = findViewById(R.id.tvAADAlarmDescription);
        bAADAccept = findViewById(R.id.bAADAccept);
        bAADCancle = findViewById(R.id.bAADCancle);

        String selectedAlarmJSON = getIntent().getStringExtra(SELECTED_ALARM_JSON_INTENT_EXTRA);
        selectedAlarm = new Gson().fromJson(selectedAlarmJSON, Alarm.class);

        StringBuilder s = new StringBuilder();
        s.append("Short description of alarm id : ").append(selectedAlarm.getEntryID()).append("\n");
        s.append("Equipment id : ").append(selectedAlarm.getEquipID()).append("\n");
        s.append("Error Type : ").append(selectedAlarm.getErrorCode()).append("\n");

        tvAADAlarmDescription.setText(s.toString());


        bAADAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(ALARM_DETAILS_ACTIVITY_RETURNING_DATA, "OK");
                selectedAlarm.setAssignedDate(sdf.format(new Date()));
                selectedAlarm.setCurrentStatus("In progress");
                List<Alarm> alarmList = Constants.getAlarmsList(new User());
                for (int i = 0; i < alarmList.size(); i++) {
                    if (alarmList.get(i).getEntryID().equals(selectedAlarm.getEntryID())){
                        Constants.getAlarmsList(new User()).remove(i);
                        Constants.getAlarmsList(new User()).add(selectedAlarm);
                    }
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        bAADCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
