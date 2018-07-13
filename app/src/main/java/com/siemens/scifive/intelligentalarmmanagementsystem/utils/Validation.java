package com.siemens.scifive.intelligentalarmmanagementsystem.utils;

import android.widget.EditText;

public class Validation {
    public static boolean cantBeEmptyET(EditText et) {
        if (et.getText().toString().trim().length() == 0) {
            et.setError("Cant be empty!!");
            et.requestFocus();
            return true;
        }

        return false;
    }
}
