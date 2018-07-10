package com.siemens.scifive.intelligentalarmmanagementsystem.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.activities.AlarmDetailsActivity;
import com.siemens.scifive.intelligentalarmmanagementsystem.adapters.AlarmsListAdapter;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.Alarm;

import java.util.ArrayList;
import java.util.List;

public class UserHomeFragment extends Fragment {

    private static UserHomeFragment INSTANCE = null;
    private ListView lvFUHAlarmsList;
    private Context mCtx;

    public static final String SELECTED_ALARM_JSON_INTENT_EXTRA = "SELECTED_ALARM_JSON_INTENT_EXTRA";

    public static UserHomeFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserHomeFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCtx = container.getContext();
        View v = inflater.inflate(R.layout.fragment_user_home, container, false);

        lvFUHAlarmsList = v.findViewById(R.id.lvFUHAlarmsList);//connecting our views with the ones in XML

        List<Alarm> alarms = new ArrayList<>();
        //WE ARE MAKING A DUMMY LIST (WE ARE GOIND TO DELETE THIS)
        alarms.add(new Alarm("1", "101", "EQ_01", "10-7-2018", "11-7-2018", "Failed"));
        alarms.add(new Alarm("2", "102", "EQ_02", "11-7-2018", "12-7-2018", "Failure"));
        alarms.add(new Alarm("3", "103", "EQ_03", "12-7-2018", "13-7-2018", "Failed"));
        //THIS MUCH DUMMY DATA IS ENOUGH

        AlarmsListAdapter adapter = new AlarmsListAdapter(mCtx, alarms, new AlarmsListAdapter.ALACallbacks() {
            @Override
            public void OnClickedItem(Alarm selectedAlarm) {
                Intent i = new Intent(mCtx, AlarmDetailsActivity.class);
                String selectedAlarmJSON = new Gson().toJson(selectedAlarm);
                i.putExtra(SELECTED_ALARM_JSON_INTENT_EXTRA, selectedAlarmJSON);
                startActivity(i);
            }
        });


        lvFUHAlarmsList.setAdapter(adapter);

        return v;
    }
}
