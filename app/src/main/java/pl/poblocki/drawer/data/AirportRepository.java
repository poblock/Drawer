package pl.poblocki.drawer.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.poblocki.drawer.R;
import pl.poblocki.drawer.data.db.AirportLocalSource;
import pl.poblocki.drawer.data.network.AirportRemoteAPI;
import pl.poblocki.drawer.data.network.AirportRemoteSource;
import pl.poblocki.drawer.data.network.FlightsResponse;
import pl.poblocki.drawer.model.Airline;
import pl.poblocki.drawer.model.Airport;
import pl.poblocki.drawer.model.Connection;
import pl.poblocki.drawer.model.DBTime;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by krzysztof.poblocki on 2017-06-20.
 */

public class AirportRepository {

    private AirportRemoteSource remoteSource;
    private AirportLocalSource localSource;

    private List<Airport> airports;
    private List<Airline> airlines;
    private List<Connection> connections;
    private List<DBTime> times;

    public AirportRepository(AirportRemoteSource remoteSource, AirportLocalSource localSource) {
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }

    public void getFlights() {
        Log.i("REPOSITORY", "getFlights");
        FlightsResponse response = remoteSource.getFlights();
        if(response!=null) {
            Log.i("REPOSITORY", "Response "+response);
        }
    }

    private void refreshCacheAirports(List<Airport> data) {
        if (airports == null) {
            airports = new ArrayList<>();
        }
        airports.clear();
        airports.addAll(data);
    }

    public List<Airport> getAirports() {
        Log.i("REPOSITORY", "getAirports");
        if(airports != null) {
            Log.i("REPOSITORY", "airports != null");
            return airports;
        } else {
            Log.i("REPOSITORY", "airports == null");
            List<Airport> localAirports = localSource.getAirports();
            if(localAirports!=null && localAirports.size()>0) {
                Log.i("REPOSITORY", "localAirports != null : "+localAirports.toString());
                refreshCacheAirports(localAirports);
                return airports;
            } else {
                Log.i("REPOSITORY", "localAirports == null");
                List<Airport> list = remoteSource.getAirports();
                if(list!=null) {
                    Log.i("REPOSITORY", "remoteAirports != null");
                    localSource.refreshAirports(list);
                    refreshCacheAirports(list);
                    return list;
                }
            }
        }
        return null;
    }
}
