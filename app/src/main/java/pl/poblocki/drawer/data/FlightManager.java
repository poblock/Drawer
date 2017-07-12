package pl.poblocki.drawer.data;

import java.util.List;

import pl.poblocki.drawer.model.Flight;

/**
 * Created by krzysztof.poblocki on 2017-06-07.
 */

public class FlightManager {

    private FlightDecoder decoder;

    public FlightManager() {
        decoder = new FlightDecoder();
    }

    public List<Flight> getFlights() {
        return Content.makeMockList(true);
    }
}
