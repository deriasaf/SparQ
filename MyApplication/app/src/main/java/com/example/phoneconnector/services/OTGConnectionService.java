package com.example.phoneconnector.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.phoneconnector.MainActivity;
import com.example.phoneconnector.R;
import com.example.phoneconnector.receivers.USBPowerReceiver;

public class OTGConnectionService extends Service
{

    private static final int FOREGROUND_SERVICE_ID = 101;
    private BroadcastReceiver usbStateReceiver;

    @Override
    public void onCreate()
    {
        Toast.makeText(getApplicationContext(), "Started", Toast.LENGTH_SHORT)
             .show();



        super.onCreate();
        usbStateReceiver = new USBPowerReceiver();

        IntentFilter filter = new IntentFilter();

//        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction("android.hardware.usb.action.USB_STATE");
//        filter.addAction("android.hardware.usb.action.USB_ACCESSORY_ATTACHED");
//        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
//        filter.addAction(Intent.ACTION_POWER_CONNECTED);

        registerReceiver(usbStateReceiver, filter);

        // Start the service as a foreground service
        startForeground(FOREGROUND_SERVICE_ID, createNotification());
    }



    @Override
    public void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(usbStateReceiver);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }



    private Notification createNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("USB Connection Detection")
                .setContentText("Checking for fan events")
                .setSmallIcon(R.mipmap.ic_launcher_round) // Replace with your notification icon
                .setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create a Notification Channel for Android 8.0 and above
            // Replace "CHANNEL_ID" with your desired channel ID
            String channelId = "CHANNEL_ID";
            String channelName = "USB Connection";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            // Set the channel ID for the notification
            builder.setChannelId(channelId);
        }

        return builder.build();
    }


}
