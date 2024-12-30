package com.example.broadcastreceiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class ConnectivityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Lấy action từ intent
        String action = intent.getAction();

        // Chỉ xử lý khi action phù hợp
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(action)) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
            if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
                Toast.makeText(context, "Wi-Fi is ON", Toast.LENGTH_SHORT).show();
            } else if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
                Toast.makeText(context, "Wi-Fi is OFF", Toast.LENGTH_SHORT).show();
            }
        } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            int bluetoothState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
            if (bluetoothState == BluetoothAdapter.STATE_ON) {
                Toast.makeText(context, "Bluetooth is ON", Toast.LENGTH_SHORT).show();
            } else if (bluetoothState == BluetoothAdapter.STATE_OFF) {
                Toast.makeText(context, "Bluetooth is OFF", Toast.LENGTH_SHORT).show();
            }
        }
    }
}