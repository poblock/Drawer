package pl.poblocki.drawer.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import android.util.Log;

public class FlightsReceiver extends BroadcastReceiver {
    private ResultReceiver mReceiver;

    public FlightsReceiver() {

    }

    public FlightsReceiver(ResultReceiver mReceiver) {
        this.mReceiver = mReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("FlightsReceiver", "onReceive -> startService");
        FlightsIntentService.startAction(context, mReceiver);
    }
}
