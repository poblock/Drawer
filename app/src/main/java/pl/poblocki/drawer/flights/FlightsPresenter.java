package pl.poblocki.drawer.flights;


import android.util.Log;

import java.util.List;

import pl.poblocki.drawer.data.Flights;
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
    public void onReceiveResult(int resultCode) {
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
