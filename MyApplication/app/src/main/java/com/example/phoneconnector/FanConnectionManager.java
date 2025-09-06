package com.example.phoneconnector;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class FanConnectionManager
{

    public static final String FAN_CONNECTION_COUNT_KEY = "fan_connection_count";
    public static final String ACTION_FAN_COUNT_UPDATED
            = "com.example.phoneconnector.ACTION_FAN_COUNT_UPDATED";
    public static final String EXTRA_FAN_COUNT = "fan_count";

    public static void incrementFanConnectionCount(Context context)
    {
        int fanConnectionCount = getFanConnectionCount(context);
        fanConnectionCount++;
        saveFanConnectionCount(context, fanConnectionCount);
        sendCountUpdateBroadcast(context, fanConnectionCount);
    }

    private static void sendCountUpdateBroadcast(Context context, int count)
    {
        Intent broadcastIntent = new Intent(ACTION_FAN_COUNT_UPDATED);
        broadcastIntent.putExtra(EXTRA_FAN_COUNT, count);
        LocalBroadcastManager.getInstance(context)
                             .sendBroadcast(broadcastIntent);
    }

    public static void saveFanConnectionCount(Context context, int count)
    {
        SharedPreferences prefs = context.getSharedPreferences("fan_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(FAN_CONNECTION_COUNT_KEY, count);
        editor.apply();
    }

    public static int getFanConnectionCount(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("fan_prefs", Context.MODE_PRIVATE);
        return (prefs.getInt(FAN_CONNECTION_COUNT_KEY, 0));
    }
}
