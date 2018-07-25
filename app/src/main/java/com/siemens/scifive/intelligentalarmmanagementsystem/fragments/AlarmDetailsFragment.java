package com.siemens.scifive.intelligentalarmmanagementsystem.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.activities.UserHomeActivity;
import com.siemens.scifive.intelligentalarmmanagementsystem.adapters.AlarmDetailListAdapter;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.Alarm;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.Constants;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.MyStorage;

import java.util.List;

public class AlarmDetailsFragment extends android.support.v4.app.Fragment {
    private static AlarmDetailsFragment INSTANCE = null;
    private ListView lvFADAlarmDetailsList;
    private Context mCtx;
    private RadioButton rbTroubleShootingHelpedYes;
    private RadioButton rbTroubleShootingHelpedNo;
    private EditText etTroubleShootingHelpedNoComment;
    private RadioButton rbExistingToolsHelpedYes;
    private RadioButton rbExistingToolsHelpedNo;
    private EditText etExistingToolsHelpedNoComment;
    private Button bSave;
    private Button bCancle;

    public static AlarmDetailsFragment getInstance() {
        if (INSTANCE == null)
            INSTANCE = new AlarmDetailsFragment();
        return INSTANCE;
    }
    AlarmDetailListAdapter alarmDetailListAdapter;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserHomeActivity.getInstance().tLAUHTabs.getTabAt(2).select();
        mCtx = container.getContext();
        View v = inflater.inflate(R.layout.fragment_alarm_details, container, false);

        lvFADAlarmDetailsList = v.findViewById(R.id.lvFADAlarmDetailsList);

        final List<Alarm> alarms = Constants.getAlarmsList(MyStorage.getInstance().getUser());

        alarmDetailListAdapter = new AlarmDetailListAdapter(mCtx, alarms, new AlarmDetailListAdapter.ADLACallbacks() {
            @Override
            public void OnResolvedButtonClick(final Alarm selectedAlarmDetail, final int position) {
                final Dialog dialog = new Dialog(mCtx);

                dialog.setContentView(R.layout.resolved_input_dialog_layout);

                dialog.setCancelable(false);

                rbTroubleShootingHelpedYes = dialog.findViewById(R.id.rbTroubleShootingHelpedYes);
                rbTroubleShootingHelpedNo = dialog.findViewById(R.id.rbTroubleShootingHelpedNo);
                etTroubleShootingHelpedNoComment = dialog.findViewById(R.id.etTroubleShootingHelpedNoComment);
                rbExistingToolsHelpedYes = dialog.findViewById(R.id.rbExistingToolsHelpedYes);
                rbExistingToolsHelpedNo = dialog.findViewById(R.id.rbExistingToolsHelpedNo);
                etExistingToolsHelpedNoComment = dialog.findViewById(R.id.etExistingToolsHelpedNoComment);
                bSave = dialog.findViewById(R.id.bSave);
                bCancle = dialog.findViewById(R.id.bCancle);

                rbTroubleShootingHelpedNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etTroubleShootingHelpedNoComment.setVisibility(View.VISIBLE);
                        selectedAlarmDetail.setExistingStepsHelpful(false);
                        Constants.d("ShraX", new Gson().toJson(Constants.getAlarmsList(MyStorage.getInstance().getUser())));
                    }
                });

                rbTroubleShootingHelpedYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etTroubleShootingHelpedNoComment.setVisibility(View.GONE);
                        selectedAlarmDetail.setExistingStepsHelpful(true);
                        Constants.d("ShraX", new Gson().toJson(Constants.getAlarmsList(MyStorage.getInstance().getUser())));
                    }
                });

                rbExistingToolsHelpedNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etExistingToolsHelpedNoComment.setVisibility(View.VISIBLE);
                        selectedAlarmDetail.setExistingToolsHelpful(false);
                    }
                });

                rbExistingToolsHelpedYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etExistingToolsHelpedNoComment.setVisibility(View.GONE);
                        selectedAlarmDetail.setExistingToolsHelpful(true);
                    }
                });

                bCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                bSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectedAlarmDetail.isExistingStepsHelpful()) {
                            selectedAlarmDetail.setExistingStepsHelpfulComment("");
                        } else {
                            selectedAlarmDetail.setExistingStepsHelpfulComment(etTroubleShootingHelpedNoComment.getText().toString().trim());
                        }

                        if (selectedAlarmDetail.isExistingToolsHelpful()){
                            selectedAlarmDetail.setExistingToolsHelpfulComment("");
                        }else{
                            selectedAlarmDetail.setExistingToolsHelpfulComment(etExistingToolsHelpedNoComment.getText().toString().trim());
                        }

                        //Constants.d("ShraX", new Gson().toJson(Constants.getAlarmsList(MyStorage.getInstance().getUser())));
                        selectedAlarmDetail.setCurrentStatus("Closed");
                        Constants.getResolvedAlarmsList().add(selectedAlarmDetail);
                        Constants.getAlarmsList(MyStorage.getInstance().getUser()).remove(position);
                        alarmDetailListAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }

            @Override
            public void OnMapButtonClick(Alarm selectedAlarmDetail, int position) {
                LatLng latLng = new LatLng(selectedAlarmDetail.getAlarmLocationLatitude(), selectedAlarmDetail.getAlarmLocationLongitude());
                UserLocationFragment.getInstance().setDestinationLatLng(latLng);
                UserLocationFragment.getInstance().switchFragmentShowPath();

            }
        });

        lvFADAlarmDetailsList.setAdapter(alarmDetailListAdapter);

        return v;
    }
}
