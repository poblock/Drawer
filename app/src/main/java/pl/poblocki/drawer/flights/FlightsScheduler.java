package pl.poblocki.drawer.flights;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

import pl.poblocki.drawer.flights.service.FlightsIntentService;
import pl.poblocki.drawer.flights.service.FlightsReceiver;

/**
 * Created by Iza on 06.08.2017.
 */

public class FlightsScheduler {

    Context context;
    private static final long TIME = 5 * 1000; // 5 sek.
    private AlarmManager am;
    private FlightsReceiver alarmReceiver;
    private static final String TAG = "FlightsScheduler";

    public FlightsScheduler(Context context) {
        this.context = context;
    }

    private void init() {
        ResultReceiver mReceiver = new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                String result = resultData.getString(FlightsIntentService.BUNDLE_KEY_REQUEST_RESULT);
                Log.d(TAG, "onReceiveResult "+resultCode+" "+result);
//                onResult(resultCode);
            }
        };
        alarmReceiver = new FlightsReceiver(mReceiver);
    }

    private void setSchedule() {
        context.registerReceiver(alarmReceiver, new IntentFilter("com.eat.alarmreceiver"));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, new Intent("com.eat.alarmreceiver"),
                PendingIntent.FLAG_UPDATE_CURRENT);
        am = (AlarmManager)(context.getSystemService( Context.ALARM_SERVICE ));
        long triggerAt = 0;
        am.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAt, TIME, pendingIntent);
    }
}
