package com.siemens.scifive.intelligentalarmmanagementsystem.dtos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.adapters.AlarmsListAdapter;

import java.util.List;

public class AlarmDetailListAdapter extends BaseAdapter {
    Context mCtx;
    List<AlarmDetail> alarmDetails;
    ADLACallbacks c;

    public interface ADLACallbacks {
        public void OnResolvedButtonClick(AlarmDetail selectedAlarmDetail);
    }
    public AlarmDetailListAdapter(Context mCtx, List<AlarmDetail> alarmDetails, ADLACallbacks c) {
        this.mCtx = mCtx;
        this.alarmDetails = alarmDetails;
        this.c=c;
    }

    @Override
    public int getCount() {
        return alarmDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return alarmDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ADLAViewHolder h;
        if (v == null) {
            v = LayoutInflater.from(mCtx).inflate(R.layout.alarm_details_list_item_layout, parent, false);
            h = new ADLAViewHolder(v);
            v.setTag(h);
        } else {
            h = (ADLAViewHolder) v.getTag();
        }

        final AlarmDetail d = (AlarmDetail) getItem(position);

        StringBuilder s = new StringBuilder();
        s.append("Detail description of alarm id : ").append(d.getAlarmId()).append("\n");
        s.append("Equipment id : ").append(d.getEquipmentId()).append("\n");
        s.append("Error code : ").append(d.getErrorCode()).append("\n");
        s.append("current Status : ").append(d.getCurrentStatus()).append("\n");
        s.append("Troubleshooting Steps : ").append(d.getTroubleshootingSteps()).append("\n");
        s.append("Tools : ").append(d.getTools()).append("\n");

        h.tvADLILDescription.setText(s.toString());

        h.bADLILResolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.OnResolvedButtonClick(d);
            }
        });

        return v;
    }

    static class ADLAViewHolder {
        TextView tvADLILDescription;
        Button bADLILResolved;

        public ADLAViewHolder(View v) {
            tvADLILDescription = v.findViewById(R.id.tvADLILDescription);
            bADLILResolved = v.findViewById(R.id.bADLILResolved);
        }
    }
}
