package pl.poblocki.drawer.data;

import java.util.List;

import pl.poblocki.drawer.model.Airline;
import pl.poblocki.drawer.model.Airport;
import pl.poblocki.drawer.model.DBTime;

/**
 * Created by krzysztof.poblocki on 2017-07-12.
 */

public interface IRepository {
    List<DBTime> getDBTime();
    List<Airport> getAirports();
    List<Airline> getAirlines();
}
