package com.siemens.scifive.intelligentalarmmanagementsystem.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemens.scifive.intelligentalarmmanagementsystem.R;

public class HistoryFragment extends Fragment{

    private static HistoryFragment INSTANCE = null;
    public static HistoryFragment getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new HistoryFragment();
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container,false);

        //main code

        return v;
    }
}

