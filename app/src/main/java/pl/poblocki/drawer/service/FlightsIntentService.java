package pl.poblocki.drawer.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlightsIntentService extends IntentService {
    private static final String ACTION_FOO = "pl.poblock.eatapplication.chapter12.alarm.service.action.FOO";
    public static final String INTENT_KEY_RECEIVER = "com.eat.INTENT_KEY_RECEIVER";
    public static final String BUNDLE_KEY_REQUEST_RESULT = "com.eat.BUNDLE_KEY_REQUEST_RESULT";

    private static final String TAG = "FlightsIntentService";
//    private AirportRemoteAPI api;

    public FlightsIntentService() {
        super("FlightsIntentService");
        Log.i(TAG, "FlightsIntentService");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://agile-dawn-87098.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient().newBuilder().readTimeout(10, TimeUnit.MINUTES).build())
                .build();
//        api = retrofit.create(AirportRemoteAPI.class);
    }

    public static void startAction(Context context, ResultReceiver mReceiver) {
        Log.i(TAG, "startAction");
        Intent intent = new Intent(context, FlightsIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(INTENT_KEY_RECEIVER, mReceiver);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "onHandleIntent");
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                ResultReceiver receiver = intent.getParcelableExtra(INTENT_KEY_RECEIVER);
                if(receiver!=null) {
                    fetchData(receiver);
                }
            }
        }
    }

    private void fetchData(final ResultReceiver receiver) {
        Log.d(TAG, "fetchData");
//        RemoteCallback callback = new RemoteCallback(this, receiver);
//        api.getFlights().enqueue(callback);
    }
}
