package com.siemens.scifive.intelligentalarmmanagementsystem.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.activities.UserHomeActivity;
import com.siemens.scifive.intelligentalarmmanagementsystem.adapters.AlarmsHistoryListAdapter;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.Alarm;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.Constants;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.MyAlertDialog;

public class HistoryFragment extends Fragment {

    private static HistoryFragment INSTANCE = null;
    ListView lvFHAlarmsList;
    Context mCtx;

    public static HistoryFragment getInstance() {
        if (INSTANCE == null)
            INSTANCE = new HistoryFragment();
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserHomeActivity.getInstance().tLAUHTabs.getTabAt(3).select();
        mCtx = container.getContext();
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        //main code
        lvFHAlarmsList = v.findViewById(R.id.lvFHAlarmsList);

        AlarmsHistoryListAdapter adapter = new AlarmsHistoryListAdapter(mCtx, Constants.getResolvedAlarmsList(), new AlarmsHistoryListAdapter.ALACallbacks() {
            @Override
            public void OnClickedItem(Alarm d, int position) {

                StringBuilder s = new StringBuilder();
                s.append("Detail description of alarm id : ").append(d.getEntryID()).append("\n");
                s.append("Equipment id : ").append(d.getEquipID()).append("\n");
                s.append("Error code : ").append(d.getErrorCode()).append("\n");
                s.append("current Status : ").append(d.getCurrentStatus()).append("\n");
                s.append("Troubleshooting Steps : ").append(d.getTroubleshootingStepsCompany()).append("\n");
                s.append("User Comments : ").append(d.getExistingStepsHelpfulComment()).append("\n");
                s.append("Tools : ").append(d.getToolsCompany()).append("\n");
                s.append("User Comments : ").append(d.getExistingToolsHelpfulComment()).append("\n");
                MyAlertDialog.show(mCtx, "Details", s.toString());
            }
        });
        lvFHAlarmsList.setAdapter(adapter);

        return v;
    }
}

