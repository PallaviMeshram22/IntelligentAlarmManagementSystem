package com.siemens.scifive.intelligentalarmmanagementsystem.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemens.scifive.intelligentalarmmanagementsystem.R;

public class UserLocationFragment extends Fragment {
    private static UserLocationFragment INSTANCE = null;

    public static UserLocationFragment getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserLocationFragment();
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user_location, container, false);
        return v;
    }
}
