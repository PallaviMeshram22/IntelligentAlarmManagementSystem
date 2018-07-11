package com.siemens.scifive.intelligentalarmmanagementsystem.firebase;

import android.app.Service;
import android.os.Vibrator;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService
{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(2000);
    }
}
