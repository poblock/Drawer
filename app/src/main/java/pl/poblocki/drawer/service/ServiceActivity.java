package pl.poblocki.drawer.service;

import android.app.ListActivity;
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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.poblocki.drawer.R;


public class ServiceActivity extends ListActivity implements ServiceConnection {

    private ArrayAdapter<String> adapter;
    private List<String> wordList;
    private AirportService s;

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
        wordList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, wordList);
        setListAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(MyIntentService.NOTIFICATION));
        Intent intent = new Intent(this, AirportService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
        unbindService(this);
    }

    public void startAirportService(View view) {
        startService(new Intent(getBaseContext(), AirportService.class));
    }

    public void stopAirportService(View view) {
        stopService(new Intent(getBaseContext(), AirportService.class));
    }

    public void onClick(View view) {
//        Intent intent = new Intent(this, MyIntentService.class);
//        intent.putExtra("filename", "index.html");
//        intent.putExtra("url", "http://www.vogella.com/index.html");
//        startService(intent);

        switch (view.getId()) {
            case R.id.updateList:
                if (s != null) {
                    Toast.makeText(this, "Number of elements" + s.getWordList().size(), Toast.LENGTH_SHORT).show();
                    wordList.clear();
                    wordList.addAll(s.getWordList());
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.triggerServiceUpdate:
                Intent service = new Intent(getApplicationContext(), AirportService.class);
                getApplicationContext().startService(service);
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        AirportService.MyBinder b = (AirportService.MyBinder) service;
        s = b.getService();
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        s = null;
    }
}
