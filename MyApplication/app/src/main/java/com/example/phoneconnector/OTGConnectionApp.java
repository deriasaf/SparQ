package com.example.phoneconnector;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.os.Build;
import android.widget.Toast;
import android.app.Application;
import android.content.SharedPreferences;

public class OTGConnectionApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel
                    channel = new NotificationChannel("CHANNEL_ID", "Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
