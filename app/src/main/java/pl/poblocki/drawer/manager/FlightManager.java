package pl.poblocki.drawer.manager;

import android.util.Log;

import com.koushikdutta.async.http.WebSocket;

import java.util.List;

import pl.poblocki.drawer.model.Airport;
import pl.poblocki.drawer.network.API;
import pl.poblocki.drawer.view.ButtonFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by krzysztof.poblocki on 2017-06-07.
 */

public class FlightManager {

    private FlightDecoder decoder;
    private API serverAPI;

    public FlightManager(API serverAPI) {
        decoder = new FlightDecoder();
        this.serverAPI = serverAPI;
    }

    public void getAirports() {
        serverAPI.getAirports().enqueue(new Callback<List<Airport>>() {
            @Override
            public void onResponse(Call<List<Airport>> call, Response<List<Airport>> response) {
                if (response.code() != 200) {
                    Log.e("MANAGER", response.toString());
                } else {

                    List<Airport> list = response.body();
                    Log.i("MANAGER", list.toString());
//                            .filter(foodzItem -> !foodzItem.getName().contains("ERROR"))
//                            .collect(Collectors.toList());

//                    view.showFoodz(foodzItemList);
                }
            }

            @Override
            public void onFailure(Call<List<Airport>> call, Throwable t) {
                Log.e("MANAGER", t.toString());
            }
        });
    }

    public void getData(final ButtonFragment.OnDataLoaded dataCallback) {
        WebSocket.StringCallback callback = new WebSocket.StringCallback() {
            public void onStringAvailable(String data) {
                data = data.trim();
                if (data.length() == 0) {
                    return;
                }
                try {
                    Log.i("SERVER", data);
                    decoder.decode(data);
                    Log.i("SERVER", decoder.getArrivalsTime()+" "+decoder.getArrivalsMap().toString());
                    Log.i("SERVER", decoder.getDeparturesTime()+" "+decoder.getDeparturesMap().toString());

                    dataCallback.OnUpdate(decoder.getArrivalsTime()+" "+decoder.getArrivalsMap().toString() + "\n" + decoder.getDeparturesTime()+" "+decoder.getDeparturesMap().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        };
//        connection.connect(callback);
    }
}
