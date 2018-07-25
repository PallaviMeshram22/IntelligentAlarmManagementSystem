package com.siemens.scifive.intelligentalarmmanagementsystem.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.siemens.scifive.intelligentalarmmanagementsystem.R;
import com.siemens.scifive.intelligentalarmmanagementsystem.fragments.AlarmDetailsFragment;
import com.siemens.scifive.intelligentalarmmanagementsystem.fragments.HistoryFragment;
import com.siemens.scifive.intelligentalarmmanagementsystem.fragments.UserHomeFragment;
import com.siemens.scifive.intelligentalarmmanagementsystem.fragments.UserLocationFragment;

public class UserHomeActivity extends AppCompatActivity {
    private static UserHomeActivity INSTANCE = null;
    FrameLayout fLAUHFragmentContainer;
    public TabLayout tLAUHTabs;
    FragmentManager fragmentManager;

    public static UserHomeActivity getInstance() {
        return INSTANCE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        INSTANCE = this;
        fLAUHFragmentContainer = findViewById(R.id.fLAUHFragmentContainer);
        tLAUHTabs = findViewById(R.id.tLAUHTabs);

        fragmentManager = getSupportFragmentManager();

        setupTabs();

        changeFragment(UserHomeFragment.getInstance(), true);
    }

    public void changeFragment(Fragment fragment, boolean isFirst) {
        if (isFirst) {
            //DONT ADD TO BACKSTACK
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fLAUHFragmentContainer, fragment)
                    .commit();
        } else {
            //ADD TO BACKSTACK
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fLAUHFragmentContainer, fragment)
                    .addToBackStack("Yes")
                    .commit();
        }
    }

    private void setupTabs() {
        TabLayout.Tab homeTab = tLAUHTabs.newTab();//POSITION 0
        homeTab.setText("Home");
        homeTab.setIcon(R.drawable.ic_home);

        TabLayout.Tab locationTab = tLAUHTabs.newTab();//POSITION 1
        locationTab.setText("Location");
        locationTab.setIcon(R.drawable.ic_location);

        TabLayout.Tab alarmDetailsTab = tLAUHTabs.newTab();//POSITION 2
        alarmDetailsTab.setText("Alarm Details");
        alarmDetailsTab.setIcon(R.drawable.ic_alarm_details);

        TabLayout.Tab historyTab = tLAUHTabs.newTab();//POSITION 3
        historyTab.setText("History");
        historyTab.setIcon(R.drawable.ic_history);


        tLAUHTabs.addTab(homeTab);
        tLAUHTabs.addTab(locationTab);
        tLAUHTabs.addTab(alarmDetailsTab);
        tLAUHTabs.addTab(historyTab);

        tLAUHTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        //showToast("Home selected");
                        changeFragment(UserHomeFragment.getInstance(), false);
                        break;
                    case 1:
                        //showToast("Location selected");
                        changeFragment(UserLocationFragment.getInstance(), false);
                        break;
                    case 2:
                        //showToast("Alarm d selected");
                        changeFragment(AlarmDetailsFragment.getInstance(), false);
                        break;
                    case 3:
                        //showToast("history selected");
                        changeFragment(HistoryFragment.getInstance(), false);
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
