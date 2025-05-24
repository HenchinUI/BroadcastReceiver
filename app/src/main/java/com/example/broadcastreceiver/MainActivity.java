package com.example.broadcastreceiver;


import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    static final String ACTION_CUSTOM_BROADCAST =
            "com.example.broadcastreceiver.ACTION_CUSTOM_BROADCAST";


    private CustomReceiver mReceiver = new CustomReceiver();


    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Register system broadcasts
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(mReceiver, filter);

        // Register custom broadcasts
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter customFilter = new IntentFilter(ACTION_CUSTOM_BROADCAST);
        mLocalBroadcastManager.registerReceiver(mReceiver, customFilter);
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        mLocalBroadcastManager.sendBroadcast(customBroadcastIntent);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
