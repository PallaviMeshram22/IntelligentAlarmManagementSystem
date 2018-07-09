package com.siemens.scifive.intelligentalarmmanagementsystem.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemens.scifive.intelligentalarmmanagementsystem.R;

public class AlarmDetailsFragment extends android.support.v4.app.Fragment{
    private static AlarmDetailsFragment INSTANCE = null;

    public static AlarmDetailsFragment getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new AlarmDetailsFragment();
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alarm_details, container, false);

        return v;
    }
}
