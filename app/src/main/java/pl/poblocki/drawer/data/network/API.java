package pl.poblocki.drawer.data.network;

import java.util.List;

import pl.poblocki.drawer.model.Airline;
import pl.poblocki.drawer.model.Airport;
import pl.poblocki.drawer.model.DBTime;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("time")
    Call<List<DBTime>> getDBTime();

    @GET("airports")
    Call<List<Airport>> getAllAirports();

    @GET("airportsLight")
    Call<List<Airport>> getBasicAirports();

    @GET("airline")
    Call<List<Airline>> getAirlines();

    @GET("flights")
    Call<FlightsResponse> getFlights();
}
