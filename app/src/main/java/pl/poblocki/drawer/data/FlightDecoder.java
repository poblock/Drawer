package pl.poblocki.drawer.data;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import pl.poblocki.drawer.model.Flight;

/**
 * Created by krzysztof.poblocki on 2017-06-07.
 */

public class FlightDecoder {

    private HashMap<String, List<Flight>> arrivalsMap;
    private HashMap<String, List<Flight>> departuresMap;
    private String arrivalsTime;
    private String departuresTime;

    public FlightDecoder() {
        arrivalsMap = new HashMap<>();
        departuresMap = new HashMap<>();
    }

    private void clear() {
        setArrivalsTime(null);
        setDeparturesTime(null);
        arrivalsMap.clear();
        departuresMap.clear();
    }

    public void decode(String data) throws JSONException {
        clear();
        int idx = data.indexOf("|");
        String wiadomosc = data.substring(idx + 1);
        JSONObject reader = new JSONObject(wiadomosc);
        String message = reader.getString("message");
        if(message!=null && message.length()>0) {
            boolean bStatus = true;
            StringTokenizer st = new StringTokenizer(message, "|");
            while(st.hasMoreTokens()) {
                String token = st.nextToken();
                StringTokenizer msg = new StringTokenizer(token, ";");
                String typ = msg.nextToken();
                if(typ.equals("A") || typ.equals("D")) {
                    String rodzaj = msg.nextToken();
                    if(rodzaj.equals("T")) { // czas
                        String time = msg.nextToken();
                        if(typ.equals("A")) {
                            setArrivalsTime(time);
                        } else {
                            setDeparturesTime(time);
                        }
                    } else {    // lot
                        String airport = msg.nextToken();
                        String flight = msg.nextToken();
                        String strToday = msg.nextToken();
                        String airline = msg.nextToken();
                        String time = msg.nextToken();
                        String timeExp = msg.nextToken();
                        String status = msg.nextToken();
                        boolean today = Boolean.parseBoolean(strToday);
                        Flight lot = new Flight(airport, flight, airline, time, timeExp.trim(), status.trim(), today);
                        addFlight(typ, rodzaj, lot);
                    }
                } else {
                    bStatus = false;
                    break;
                }
            }
        }
    }

    private void addFlight(String typ, String rodzaj, Flight flight) {
        if(typ.equals("A")) {
            List<Flight> lista = arrivalsMap.get(rodzaj);
            if(lista==null) {
                lista = new ArrayList<>();
                arrivalsMap.put(rodzaj, lista);
            }
            lista.add(flight);

        } else {
            List<Flight> lista = departuresMap.get(rodzaj);
            if(lista==null) {
                lista = new ArrayList<>();
                departuresMap.put(rodzaj, lista);
            }
            lista.add(flight);
        }
    }

    public HashMap<String, List<Flight>> getArrivalsMap() {
        return arrivalsMap;
    }

    public String getArrivalsTime() {
        return arrivalsTime;
    }

    public void setArrivalsTime(String arrivalsTime) {
        this.arrivalsTime = arrivalsTime;
    }

    public HashMap<String, List<Flight>> getDeparturesMap() {
        return departuresMap;
    }

    public String getDeparturesTime() {
        return departuresTime;
    }

    public void setDeparturesTime(String departuresTime) {
        this.departuresTime = departuresTime;
    }
}
