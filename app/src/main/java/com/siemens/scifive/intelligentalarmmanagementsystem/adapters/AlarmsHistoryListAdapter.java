package com.siemens.scifive.intelligentalarmmanagementsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.Alarm;

import java.util.List;

public class AlarmsHistoryListAdapter extends BaseAdapter {
    Context mCtx;
    List<Alarm> alarms;
    ALACallbacks c;

    public AlarmsHistoryListAdapter(Context mCtx, List<Alarm> alarms, ALACallbacks c) {
        this.mCtx = mCtx;
        this.alarms = alarms;
        this.c = c;
    }

    @Override
    public int getCount() {
        return alarms.size();
    }

    @Override
    public Object getItem(int position) {
        return alarms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        ALAViewHolder h;
        if (v == null) {
            v = LayoutInflater.from(mCtx).inflate(R.layout.alarms_list_item_layout, parent, false);
            h = new ALAViewHolder(v);
            v.setTag(h);
        } else {
            h = (ALAViewHolder) v.getTag();
        }

        final Alarm a = (Alarm) getItem(position);

        h.tvALILAlarmId.setText(a.getEntryID());
        h.tvALILAssignedDate.setText(a.getAssignedDate());
        h.tvALILEqptId.setText(a.getEquipID());
        h.tvALILCurrentStatus.setText(a.getCurrentStatus());
        h.tvALILErrorId.setText(a.getErrorCode());
        h.tvALILRaisedDate.setText(a.getRaisedDate());

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.OnClickedItem(a, position);
            }
        });

        return v;
    }

    public interface ALACallbacks {
        public void OnClickedItem(Alarm selectedAlarm, int position);
    }

    static class ALAViewHolder {
        TextView tvALILAlarmId;
        TextView tvALILErrorId;
        TextView tvALILEqptId;
        TextView tvALILRaisedDate;
        TextView tvALILAssignedDate;
        TextView tvALILCurrentStatus;

        public ALAViewHolder(View v) {
            tvALILAlarmId = v.findViewById(R.id.tvALILAlarmId);
            tvALILErrorId = v.findViewById(R.id.tvALILErrorId);
            tvALILEqptId = v.findViewById(R.id.tvALILEqptId);
            tvALILRaisedDate = v.findViewById(R.id.tvALILRaisedDate);
            tvALILAssignedDate = v.findViewById(R.id.tvALILAssignedDate);
            tvALILCurrentStatus = v.findViewById(R.id.tvALILCurrentStatus);
        }
    }


}
