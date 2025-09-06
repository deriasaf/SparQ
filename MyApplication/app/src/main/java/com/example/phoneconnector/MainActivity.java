package com.example.phoneconnector;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.phoneconnector.interfaces.ChargerEvent;
import com.example.phoneconnector.services.OTGConnectionService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity
{

    private TextView countTextView;
    private Button resetButton;
//    private BroadcastReceiver fanCountReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTextView = findViewById(R.id.countTextView);
        resetButton = findViewById(R.id.resetButton);
        displayCount();
        setupNotificationAndLocalBroadcast();

        resetButton.setOnClickListener(v ->
                                       {
                                           new AlertDialog.Builder(MainActivity.this).setTitle("Clear Count")
                                                                                     .setMessage("This will be set to 0. Are you sure to clear?")
                                                                                     .setPositiveButton("Clear", new DialogInterface.OnClickListener()
                                                                                     {
                                                                                         @Override
                                                                                         public void onClick(
                                                                                                 DialogInterface dialog,
                                                                                                 int which)
                                                                                         {
                                                                                             FanConnectionManager.saveFanConnectionCount(MainActivity.this, 0);
                                                                                             displayCount();
                                                                                         }
                                                                                     })
                                                                                     .setNegativeButton("Dismiss", null)
                                                                                     .show();
                                       });

    }


    void setupNotificationAndLocalBroadcast()
    {

        if (requestNotificationPermission())
        {
            startService(new Intent(this, OTGConnectionService.class));
        }

//        fanCountReceiver = new BroadcastReceiver()
//        {
//            @Override
//            public void onReceive(Context context, Intent intent)
//            {
//                if (FanConnectionManager.ACTION_FAN_COUNT_UPDATED.equals(intent.getAction()))
//                {
//                    displayCount();
//                }
//            }
//        };
//        LocalBroadcastManager.getInstance(this)
//                             .registerReceiver(fanCountReceiver, new IntentFilter(FanConnectionManager.ACTION_FAN_COUNT_UPDATED));


    }

    @Override
    protected void onStart()
    {
        EventBus.getDefault()
                .register(this);
        super.onStart();
    }

    @Override
    protected void onStop()
    {
        EventBus.getDefault()
                .unregister(this);
        super.onStop();
    }


    // Method to request the POST_NOTIFICATIONS permission
//    @RequiresApi (api = Build.VERSION_CODES.TIRAMISU)
    private boolean requestNotificationPermission()
    {
        if (Build.VERSION.SDK_INT < 30)
        {
            return true;
        }
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 0);

        return ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
               PackageManager.PERMISSION_GRANTED;
    }


    public void check(View view)
    {
        displayCount();
    }

    void displayCount()
    {
        int fanCount = (FanConnectionManager.getFanConnectionCount(this));
        countTextView.setText(fanCount + "");
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onChargerEvent(ChargerEvent event)
    {
        boolean isCharging = event.isCharging();
        if (isCharging)
        {
            displayCount();
        }
    }

}

