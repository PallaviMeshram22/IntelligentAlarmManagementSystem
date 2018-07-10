package com.siemens.scifive.intelligentalarmmanagementsystem.activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.siemens.scifive.intelligentalarmmanagementsystem.R;

public class UserHomeActivity extends AppCompatActivity {
    FrameLayout fLAUHFragmentContainer;
    TabLayout tLAUHTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        fLAUHFragmentContainer = findViewById(R.id.fLAUHFragmentContainer);
        tLAUHTabs = findViewById(R.id.tLAUHTabs);

        setupTabs();
    }

    private void setupTabs() {
        TabLayout.Tab homeTab = tLAUHTabs.newTab();//POSITION 0
        homeTab.setText("Home");
        homeTab.setIcon(R.drawable.home);

        TabLayout.Tab locationTab = tLAUHTabs.newTab();//POSITION 1
        locationTab.setText("Location");
        locationTab.setIcon(R.drawable.location);

        TabLayout.Tab alarmDetailsTab = tLAUHTabs.newTab();//POSITION 2
        alarmDetailsTab.setText("Alarm Details");
        alarmDetailsTab.setIcon(R.drawable.alarm_details);

        TabLayout.Tab historyTab = tLAUHTabs.newTab();//POSITION 3
        historyTab.setText("History");
        historyTab.setIcon(R.drawable.history);


        tLAUHTabs.addTab(homeTab);
        tLAUHTabs.addTab(locationTab);
        tLAUHTabs.addTab(alarmDetailsTab);
        tLAUHTabs.addTab(historyTab);

        tLAUHTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        showToast("Home selected");
                        break;
                    case 1:
                        showToast("Location selected");
                        break;
                    case 2:
                        showToast("Alarm d selected");
                        break;
                    case 3:
                        showToast("history selected");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(UserHomeActivity.this, text, Toast.LENGTH_SHORT).show();
    }

}
