package com.example.phoneconnector.receivers;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.phoneconnector.FanConnectionManager;
import com.example.phoneconnector.interfaces.ChargerEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.Set;

public class USBPowerReceiver extends BroadcastReceiver
{

    private static final String ACTION_USB_PERMISSION = "com.example.yourapp.USB_PERMISSION";

    @Override
    public void onReceive(Context context, Intent intent)
    {


//        Log.d("TAG", "onCreate: getExtras: " + intent.getExtras());
//        Log.d("TAG", "onCreate: getCategories: " + intent.getCategories());
//        Log.d("TAG", "onCreate: getData: " + intent.getData());
//        Log.d("TAG", "onCreate: getDataString: " + intent.getAction());
//        Log.d("TAG", "onCreate: getDataString: " + intent.getDataString());


//        Toast.makeText(context, intent.getAction(), Toast.LENGTH_SHORT)
//             .show();


//        String message = getAllValues(intent.getExtras());
//        Log.d("BC Data: " + intent.getAction(), message);
//
//                try
//                {
//                    AlertDialog alertDialog = new AlertDialog.Builder(context)
//                            .setTitle("Data Received")
//                            .setMessage(message)
//                            .setNegativeButton("Ok", null)
//                            .create();
//
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
//                    } else {
//                        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//                    }
//                    alertDialog.show();
//                } catch (Exception ignored){
//                    Log.d("TAG", "exception: " + ignored.getMessage());
//                }


//        if (intent.getAction() != null && intent.getAction().equals(UsbManager.ACTION_USB_DEVICE_ATTACHED)) {
//            // A USB device is attached
//            UsbDevice usbDevice = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
//            if (usbDevice != null) {
//                // Handle the attached USB device
//                String deviceName = usbDevice.getDeviceName();
//                String productName = usbDevice.getProductName();
//                String manufacturerName = usbDevice.getManufacturerName();
//
//                Log.d("USBConnectionReceiver", "USB Device Attached:");
//                Log.d("USBConnectionReceiver", "Device Name: " + deviceName);
//                Log.d("USBConnectionReceiver", "Product Name: " + productName);
//                Log.d("USBConnectionReceiver", "Manufacturer Name: " + manufacturerName);
//
//
//                UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
//                PendingIntent permissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), PendingIntent.FLAG_IMMUTABLE);
//                usbManager.requestPermission(usbDevice, permissionIntent);
//                // You have the UsbDevice object and can use it to interact with the connected USB device.
//            } else {
//                Log.e("USBConnectionReceiver", "No UsbDevice found in the attached broadcast.");
//            }
//        }

//        Log.d("TAG", "onReceive: " + message);
//        FanConnectionManager.incrementFanConnectionCount(context);
                        NotificationHelper.showWelcomeNotification(context);
//        EventBus.getDefault().post(new ChargerEvent(true));


                if (intent.getAction() != null && intent.getAction()
                                                        .equals("android.hardware.usb.action.USB_STATE"))


                {


                    boolean connected = intent.getBooleanExtra("host_connected", false);
                    if (connected)
                    {
                        // A USB device is connected, incrementing the fan connection count here
                        FanConnectionManager.incrementFanConnectionCount(context);
                        NotificationHelper.showWelcomeNotification(context);
                        EventBus.getDefault().post(new ChargerEvent(true));
                        Log.d("UsbConnectionService", "USB Device Connected");
                    }
                }
//        if (intent.getAction() != null && intent.getAction()
//                                                        .equals(UsbManager.ACTION_USB_DEVICE_ATTACHED))
//
//
//                {
//                    FanConnectionManager.incrementFanConnectionCount(context);
                        NotificationHelper.showWelcomeNotification(context);
//                    EventBus.getDefault().post(new ChargerEvent(true));
//                    Log.d("UsbConnectionService", "USB Device Connected");
//                }
//        if (intent.getAction() != null && intent.getAction()
//                                                        .equals("android.hardware.usb.action.USB_ACCESSORY_ATTACHED"))
//
//
//                {
//                    FanConnectionManager.incrementFanConnectionCount(context);
                        NotificationHelper.showWelcomeNotification(context);
//                    EventBus.getDefault().post(new ChargerEvent(true));
//                    Log.d("UsbConnectionService", "USB Device Connected");
//                }
    }


//    private String getAllValues(Bundle extras)
//    {
//        String message = "";
//        if (extras != null) {
//            // Get all the keys present in the Bundle
//            Set<String> keys = extras.keySet();
//
//            // Iterate through the keys and print out the corresponding values
//            for (String key : keys) {
//                Object value = extras.get(key);
//                message += key + ": " + value + "\n";
//            }
//        }
//
//        return message;
//    }
}
