package com.peppe.popapp.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.audiofx.BassBoost;
import android.support.v4.app.NotificationCompat;

import com.peppe.popapp.R;

public class MyNotificationManager {

    private Context ctx;

    public static final int NOTIFICATION_ID = 234;

    public  MyNotificationManager(Context ctx){
        this.ctx = ctx;
    }

    public void showNotification(String notification, Intent intent){

        PendingIntent pendingIntent = PendingIntent.getActivities(
                ctx,
                NOTIFICATION_ID,
                new Intent[]{intent},
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);

        Notification mNotification = builder.setSmallIcon(R.drawable.icona)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentText(notification)
                .setLargeIcon(BitmapFactory.decodeResource(ctx.getResources(), R.drawable.icona))
                .build();

        mNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, mNotification);

    }
}
