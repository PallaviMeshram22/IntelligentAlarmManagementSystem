package com.siemens.scifive.intelligentalarmmanagementsystem.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;

public class MyAlertDialog {
    private static AlertDialog.Builder builder;

    private static void prepare(Context mCtx, String title, String message) {
        builder = new AlertDialog.Builder(mCtx);
        builder.setTitle(title);
        builder.setMessage(message);
    }

    public static void showNetworkErrorDialog(Context mCtx) {
        prepare(mCtx, "Network Error", "There is some error with your internet, Please try again later");
        builder.show();
    }

    public static void showDataErrorDialog(Context mCtx, Exception e) {
        prepare(mCtx, "Data error", "Data error please contact with admin\nError : "+e.getMessage());
        builder.show();
    }
}
