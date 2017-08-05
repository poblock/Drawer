package pl.poblocki.drawer.data.network;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.poblocki.drawer.R;
import pl.poblocki.drawer.data.Flights;
import pl.poblocki.drawer.details.FlightDetails;
import pl.poblocki.drawer.flights.FlightsActivity;
import pl.poblocki.drawer.flights.FlightsContract;
import pl.poblocki.drawer.model.Flight;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static pl.poblocki.drawer.data.Flights.AKTUALIZACJA;
import static pl.poblocki.drawer.data.Flights.NOWY;
import static pl.poblocki.drawer.data.Flights.USUNIETY;
import static pl.poblocki.drawer.flights.service.FlightsIntentService.BUNDLE_KEY_REQUEST_RESULT;


/**
 * Created by krzysztof.poblocki on 2017-07-24.
 */

public class RemoteCallback implements Callback<FlightsResponse>, FlightsContract.LoadFlightsCallback {
    private ResultReceiver receiver;
    private Context context;

    public RemoteCallback(Context context, ResultReceiver receiver) {
        this.context = context;
        this.receiver = receiver;
    }

    @Override
    public void onResponse(Call<FlightsResponse> call, Response<FlightsResponse> response) {
        if(response.code() == 200) {
            Flights.getInstance().onProcessResponse(response.body(), this);
        } else {
            onError();
        }
    }

    @Override
    public void onFailure(Call<FlightsResponse> call, Throwable t) {
        onError();
    }

    @Override
    public void onFlightsLoaded() {
        Bundle resultBundle = new Bundle();
        resultBundle.putString(BUNDLE_KEY_REQUEST_RESULT, "OK");
        receiver.send(200, resultBundle);
    }

    @Override
    public void onAlertsLoaded(Map<Integer, List<Flight>> alertsMap) {
        if(alertsMap!=null && alertsMap.size()>0) {
            Log.i("CALLBACK", "Alerts "+alertsMap.toString());
            Set<Map.Entry<Integer, List<Flight>>> alertsEntries = alertsMap.entrySet();
            Iterator<Map.Entry<Integer, List<Flight>>> it = alertsEntries.iterator();
            while(it.hasNext()) {
                Map.Entry<Integer, List<Flight>> entry = it.next();
                for(Flight f : entry.getValue()) {
                    createNotification(entry.getKey(), f);
                }
            }
        }
    }

    @Override
    public void onError() {
        Bundle resultBundle = new Bundle();
        resultBundle.putString(BUNDLE_KEY_REQUEST_RESULT, "ERROR");
        receiver.send(0, resultBundle);
    }

    private void createNotification(Integer id, Flight flight) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.oko);
        String contentText = null;
        builder.setContentTitle("NOTYFIKACJA typu "+id);
        Log.i("CALLBACK", "createNotification");
        switch (id) {
            case AKTUALIZACJA : {
                Intent intent = new Intent(context, FlightDetails.class);
                intent.putExtra(FlightDetails.EXTRA_ID, flight.getId());
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                contentText = "Aktualizacja lotu "+flight.getDestination();
                break;
            }
            case NOWY : {
                Intent intent = new Intent(context, FlightDetails.class);
                intent.putExtra(FlightDetails.EXTRA_ID, flight.getId());
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                contentText = "Nowe dane o locie do "+flight.getDestination();
                break;
            }
            case USUNIETY : {
                Intent intent = new Intent(context, FlightsActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                contentText = "Zakonczono informowanie o locie "+flight.getDestination();
                break;
            }
        }
        if(contentText!=null) {
            builder.setContentText(contentText);
        }
        NotificationManager mgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mgr.notify(id, builder.build());
    }
}
