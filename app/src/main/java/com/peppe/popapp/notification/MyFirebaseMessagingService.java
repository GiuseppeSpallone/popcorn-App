package com.peppe.popapp.notification;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.peppe.popapp.MainActivity;

import org.json.JSONObject;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "fcmMessage";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        try {
            JSONObject json = new JSONObject(remoteMessage.getData().toString());

            JSONObject data = json.getJSONObject("data");

            String title = data.getString("message");
            String message = data.getString("title");

            notifyUser(title, message);

            Log.d(TAG, "Title: " + title);
            Log.d(TAG, "Message: " + message);

        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    public void notifyUser(String title, String notification) {
        MyNotificationManager myNotificationManager = new MyNotificationManager(getApplicationContext());
        myNotificationManager.showNotification(title, notification, new Intent(getApplicationContext(), MainActivity.class));
    }


}
