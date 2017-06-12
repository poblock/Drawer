package pl.poblocki.drawer.manager;

import android.util.Log;

import com.koushikdutta.async.http.WebSocket;

import javax.inject.Inject;

import pl.poblocki.drawer.view.ButtonFragment;
import pl.poblocki.drawer.network.WSConnect;

/**
 * Created by krzysztof.poblocki on 2017-06-07.
 */

public class FlightManager {

//    private static FlightManager instance;
    @Inject
    FlightDecoder decoder;
    @Inject
    WSConnect connection;

//    public FlightManager() {
//        connection = new WSConnect();
//        decoder = new FlightDecoder();
//    }

//    public static FlightManager getInstance() {
//        if(instance==null) {
//            instance = new FlightManager();
//        }
//        return instance;
//    }

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
        connection.connect(callback);
    }
}
