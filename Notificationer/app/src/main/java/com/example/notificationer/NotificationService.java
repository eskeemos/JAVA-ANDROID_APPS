package com.example.notificationer;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import android.os.Handler;
import android.widget.Toast;

public class NotificationService extends IntentService {

    public static final String EXTRA_MESSAGE = "message";
    private String notificationText;
    private Handler handler;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        handler = new Handler();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        synchronized (this){
            try{
                wait(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (intent != null) {
            notificationText = intent.getStringExtra(EXTRA_MESSAGE);
        }

        getNotification(3);
    }

    private void getNotification(int choice) {
        switch (choice){
            case 1:
                Log.v("NOTIFICATION SERVICE : ", notificationText);
                break;
            case 2:
                handler.post(() -> Toast.makeText(getApplicationContext(), notificationText, Toast.LENGTH_SHORT).show());
            case 3:
                Intent intent = new Intent(this, MainActivity.class);
                TaskStackBuilder builder = TaskStackBuilder.create(this);
                builder.addParentStack(MainActivity.class);
                builder.addNextIntent(intent);

                PendingIntent pendingIntent = builder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                Notification notification = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(getString(R.string.app_name))
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setContentIntent(pendingIntent)
                    .setContentText(notificationText)
                    .build();

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(2000, notification);
        }
    }
}