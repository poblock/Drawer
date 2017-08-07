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

import java.util.List;

import pl.poblocki.drawer.data.Flights;
import pl.poblocki.drawer.flights.service.FlightsIntentService;
import pl.poblocki.drawer.flights.service.FlightsReceiver;
import pl.poblocki.drawer.model.Flight;


/**
 * Created by krzysztof.poblocki on 2017-07-14.
 */

public class FlightsPresenter implements FlightsContract.Presenter {

    private FlightsContract.View mView;
    private static final String TAG = "FlightsPresenter";

    public FlightsPresenter(FlightsContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void showFlightDetails(Flight flight) {
        mView.showFlightDetailsUI(flight);
    }

    @Override
    public void onObserveFlight(Flight flight) {
        Flights.getInstance().changeFlightObservationStatus(flight);
    }

    @Override
    public void onResult(int resultCode) {
        Log.d(TAG, "onReceiveResult "+resultCode);
        mView.showLoading(false);
        if(resultCode == 200) {
            String arrivalsTime = Flights.getInstance().getLastArrivalsTime();
            String departuresTime = Flights.getInstance().getLastDeparturesTime();
            List<Flight> arrivalsList = Flights.getInstance().getArrivals();
            List<Flight> departuresList = Flights.getInstance().getDepartures();
            if(arrivalsTime!=null && !arrivalsTime.equals("") && arrivalsList!=null && arrivalsList.size()>0) {
                mView.refreshUI(arrivalsTime, departuresTime, arrivalsList, departuresList);
            } else {
                Log.e(TAG, "arrivalsTime!=null "+(arrivalsTime!=null)+
                        " !arrivalsTime.equals(\"\") "+(!arrivalsTime.equals(""))+
                        " arrivalsList!=null "+(arrivalsList!=null)+
                        " arrivalsList.size()>0 "+(arrivalsList.size()>0));
            }
        } else {
            mView.onShowErrorUI();
        }
    }
}
