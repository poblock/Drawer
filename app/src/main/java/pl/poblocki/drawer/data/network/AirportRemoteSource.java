package pl.poblocki.drawer.data.network;

import android.util.Log;

import java.util.List;

import pl.poblocki.drawer.data.IRepository;
import pl.poblocki.drawer.model.Airline;
import pl.poblocki.drawer.model.Airport;
import pl.poblocki.drawer.model.DBTime;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by krzysztof.poblocki on 2017-07-12.
 */

public class AirportRemoteSource implements IRepository {

    private AirportRemoteAPI serverAPI;
    private List<Airport> airports;
    private FlightsResponse localResponse;

    public AirportRemoteSource(AirportRemoteAPI serverAPI) {
        this.serverAPI = serverAPI;
    }

    public FlightsResponse getFlights() {
        serverAPI.getFlights().enqueue(new Callback<FlightsResponse>() {
            @Override
            public void onResponse(Call<FlightsResponse> call, Response<FlightsResponse> response) {
                if(response.code()==200) {
                    setLocalResponse(response.body());
                } else {
                    setLocalResponse(null);
                }
            }

            @Override
            public void onFailure(Call<FlightsResponse> call, Throwable t) {
                setLocalResponse(null);
            }
        });
        return localResponse;
    }

    @Override
    public List<DBTime> getDBTime() {
        return null;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public void setLocalResponse(FlightsResponse localResponse) {
        this.localResponse = localResponse;
    }

    @Override
    public List<Airport> getAirports() {
        serverAPI.getBasicAirports().enqueue(new Callback<List<Airport>>() {
                    @Override
                    public void onResponse(Call<List<Airport>> call, Response<List<Airport>> response) {
                        if (response.code() != 200) {
                            Log.e("REMOTE", response.toString());
                            setAirports(null);
                        } else {
                            setAirports(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Airport>> call, Throwable t) {
                        Log.e("REMOTE", t.toString());
                        setAirports(null);
                    }
        });
        return airports;
    }

    @Override
    public List<Airline> getAirlines() {
        return null;
    }
}
