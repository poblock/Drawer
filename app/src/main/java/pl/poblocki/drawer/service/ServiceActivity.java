package pl.poblocki.drawer.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pl.poblocki.drawer.R;


public class ServiceActivity extends AppCompatActivity implements ServiceConnection {

    String msg = "Android : ";

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                int local = bundle.getInt("value");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(MyIntentService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void startAirportService(View view) {
        startService(new Intent(getBaseContext(), AirportService.class));
    }

    public void stopAirportService(View view) {
        stopService(new Intent(getBaseContext(), AirportService.class));
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("filename", "index.html");
        intent.putExtra("url", "http://www.vogella.com/index.html");
        startService(intent);
//        textView.setText("Service started");
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
