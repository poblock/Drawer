package pl.poblocki.drawer.data;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.poblocki.drawer.data.db.AirportLocalSource;
import pl.poblocki.drawer.model.Airline;
import pl.poblocki.drawer.model.Airport;
import pl.poblocki.drawer.model.Connection;
import pl.poblocki.drawer.model.DBTime;
import pl.poblocki.drawer.data.network.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by krzysztof.poblocki on 2017-06-20.
 */

public class AirportRepository {

//    private Context context;
    private API remoteSource;
    private AirportLocalSource localSource;

    private List<Airport> airports;
    private List<Airline> airlines;
    private List<Connection> connections;
    private List<DBTime> times;

    public AirportRepository(API remoteSource, AirportLocalSource localSource) {
        this.remoteSource = remoteSource;
        this.localSource = localSource;
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
            List<Airport> localAirports = localSource.getAllAirports();
            if(localAirports!=null && localAirports.size()>0) {
                Log.i("REPOSITORY", "localAirports != null");
                Log.i("REPOSITORY", localAirports.toString());
                refreshCacheAirports(localAirports);
                return airports;
            } else {
                Log.i("REPOSITORY", "localAirports == null");
                remoteSource.getBasicAirports().enqueue(new Callback<List<Airport>>() {
                    @Override
                    public void onResponse(Call<List<Airport>> call, Response<List<Airport>> response) {
                        if (response.code() != 200) {
                            Log.e("REPOSITORY", response.toString());
                        } else {
                            List<Airport> list = response.body();
                            if(list!=null) {
                                localSource.refreshAirports(list);
                                refreshCacheAirports(list);
                                Log.i("REPOSITORY", list.toString());
                            } else {
                                Log.e("REPOSITORY", "remote list == null");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Airport>> call, Throwable t) {
                        Log.e("REPOSITORY", toString());
                    }
                });
            }
        }

//        if (mCacheIsDirty) {
//            // If the cache is dirty we need to fetch new data from the network.
//            getTasksFromRemoteDataSource(callback);
//        } else {
//
//        }
        return null;
    }


}
