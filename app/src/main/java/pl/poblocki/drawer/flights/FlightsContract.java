package pl.poblocki.drawer.flights;

import java.util.List;
import java.util.Map;

import pl.poblocki.drawer.model.Flight;

/**
 * Created by krzysztof.poblocki on 2017-07-14.
 */

public interface FlightsContract {
    interface View {
        void showFlightDetailsUI(Flight flight);
        void setPresenter(Presenter presenter);
        void refreshUI(String arrivalsTime, String departuresTime, List<Flight> arrivals, List<Flight> departures);
        void showLoading(boolean show);
        void onShowErrorUI();
    }

    interface Presenter {
        void showFlightDetails(Flight flight);
        void onObserveFlight(Flight flight);
        void onReceiveResult(int resultCode);
    }

    interface LoadFlightsCallback {
        void onFlightsLoaded();
        void onAlertsLoaded(Map<Integer, List<Flight>> alertsMap);
        void onError();
    }
}
