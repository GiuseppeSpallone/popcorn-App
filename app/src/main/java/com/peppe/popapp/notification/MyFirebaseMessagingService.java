package com.peppe.popapp.notification;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.peppe.popapp.MainActivity;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "fcmMessage";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String message = remoteMessage.getData().get("message");

        Log.d(TAG, "Message: " + message);

        notifyUser(message);
    }

    public void notifyUser(String notification) {
        MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext());
        myNotificationManager.showNotification(notification, new Intent(getApplicationContext(), MainActivity.class));
    }



}
