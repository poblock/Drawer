package pl.poblocki.drawer.network;

import java.util.List;

import pl.poblocki.drawer.model.Airline;
import pl.poblocki.drawer.model.Airport;
import pl.poblocki.drawer.model.FlightsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("airports")
    Call<List<Airport>> getAirports();

    @GET("airline")
    Call<List<Airline>> getAirlines();

    @GET("flights")
    Call<FlightsResponse> getFlights();
}
