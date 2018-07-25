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

import com.google.gson.Gson;
import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.activities.AlarmDetailsActivity;
import com.siemens.scifive.intelligentalarmmanagementsystem.activities.UserHomeActivity;
import com.siemens.scifive.intelligentalarmmanagementsystem.adapters.AlarmsListAdapter;
import com.siemens.scifive.intelligentalarmmanagementsystem.dtos.Alarm;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.Constants;
import com.siemens.scifive.intelligentalarmmanagementsystem.utils.MyStorage;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.siemens.scifive.intelligentalarmmanagementsystem.activities.AlarmDetailsActivity.ALARM_DETAILS_ACTIVITY_RETURNING_DATA;

public class UserHomeFragment extends Fragment {

    private static UserHomeFragment INSTANCE = null;
    private ListView lvFUHAlarmsList;
    private Context mCtx;

    public static final String SELECTED_ALARM_JSON_INTENT_EXTRA = "SELECTED_ALARM_JSON_INTENT_EXTRA";
    public static final int ALARM_DETAILS_ACTIVITY_REQUEST_CODE = 101;

    public static UserHomeFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserHomeFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserHomeActivity.getInstance().tLAUHTabs.getTabAt(0).select();
        mCtx = container.getContext();
        View v = inflater.inflate(R.layout.fragment_user_home, container, false);

        lvFUHAlarmsList = v.findViewById(R.id.lvFUHAlarmsList);//connecting our views with the ones in XML

        List<Alarm> alarms = Constants.getAlarmsList(MyStorage.getInstance().getUser());
        AlarmsListAdapter adapter = new AlarmsListAdapter(mCtx, alarms, new AlarmsListAdapter.ALACallbacks() {
            @Override
            public void OnClickedItem(Alarm selectedAlarm, int position) {
                Intent i = new Intent(mCtx, AlarmDetailsActivity.class);
                String selectedAlarmJSON = new Gson().toJson(selectedAlarm);
                i.putExtra(SELECTED_ALARM_JSON_INTENT_EXTRA, selectedAlarmJSON);
                startActivityForResult(i, ALARM_DETAILS_ACTIVITY_REQUEST_CODE);
            }
        });
        lvFUHAlarmsList.setAdapter(adapter);


        //String EngID = MyStorage.getInstance().getEngID();

        //<editor-fold desc="WEBSERVICE CALL">
        /*Home.fireWSCall(mCtx, new Home.RequestDTO(EngID), new GenericWSCallback()
         {

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
                MyAlertDialog.showDataErrorDialog(mCtx,e);
            }

            @Override
            public void onSuccess(Object o) {
                Home.ResponseDTO responseDTO = (Home.ResponseDTO) o;

                AlarmsListAdapter adapter = new AlarmsListAdapter(mCtx, responseDTO.getAlarms(), new AlarmsListAdapter.ALACallbacks() {
                    @Override
                    public void OnClickedItem(Alarm selectedAlarm) {
                        Intent i = new Intent(mCtx, AlarmDetailsActivity.class);
                        String selectedAlarmJSON = new Gson().toJson(selectedAlarm);
                        i.putExtra(SELECTED_ALARM_JSON_INTENT_EXTRA, selectedAlarmJSON);
                        startActivity(i);
                    }
                });



                lvFUHAlarmsList.setAdapter(adapter);


            }
        }); */
        //</editor-fold>


        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if(requestCode==ALARM_DETAILS_ACTIVITY_REQUEST_CODE){
                String activityResult = data.getStringExtra(ALARM_DETAILS_ACTIVITY_RETURNING_DATA );

                if (activityResult.equals("OK")){
                    //change to third fragment
                    UserHomeActivity.getInstance().changeFragment(AlarmDetailsFragment.getInstance(), false);
                }


            }
        }
    }
}
