package com.siemens.scifive.intelligentalarmmanagementsystem.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.AlarmDetail;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.AlarmDetailListAdapter;

import java.util.ArrayList;
import java.util.List;

public class AlarmDetailsFragment extends android.support.v4.app.Fragment {
    private static AlarmDetailsFragment INSTANCE = null;
    private ListView lvFADAlarmDetailsList;

    public static AlarmDetailsFragment getInstance() {
        if (INSTANCE == null)
            INSTANCE = new AlarmDetailsFragment();
        return INSTANCE;
    }
    private Context mCtx;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCtx=container.getContext();
        View v = inflater.inflate(R.layout.fragment_alarm_details, container, false);

        lvFADAlarmDetailsList = v.findViewById(R.id.lvFADAlarmDetailsList);


        List<AlarmDetail> alarmDetails = new ArrayList<>();
        //ADD DUMMY DATA WHICH WERE GOING TO DELETE
        alarmDetails.add(new AlarmDetail("1","EQ_01", "101","Failed","Anything", "anything"));
        alarmDetails.add(new AlarmDetail("2","EQ_02", "102","Failed","Anything2", "anything2"));
        alarmDetails.add(new AlarmDetail("3","EQ_03", "103","Failed","Anything3", "anything3"));

        AlarmDetailListAdapter alarmDetailListAdapter = new AlarmDetailListAdapter(mCtx, alarmDetails, new AlarmDetailListAdapter.ADLACallbacks() {
            @Override
            public void OnResolvedButtonClick(AlarmDetail selectedAlarmDetail) {

            }
        });

        lvFADAlarmDetailsList.setAdapter(alarmDetailListAdapter);

        return v;
    }
}
