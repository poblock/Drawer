package pl.poblocki.drawer.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pl.poblocki.drawer.data.network.FlightsResponse;
import pl.poblocki.drawer.flights.FlightsContract;
import pl.poblocki.drawer.model.Flight;
import pl.poblocki.drawer.support.Logger;

import static pl.poblocki.drawer.model.Flight.CURRENT_DAY;
import static pl.poblocki.drawer.model.Flight.DESTINATION;
import static pl.poblocki.drawer.model.Flight.FLIGHT;
import static pl.poblocki.drawer.model.Flight.FREIGHTER;
import static pl.poblocki.drawer.model.Flight.STATUS;
import static pl.poblocki.drawer.model.Flight.TIME;
import static pl.poblocki.drawer.model.Flight.TIME_EXP;


/**
 * Created by krzysztof.poblocki on 2017-07-14.
 */

public class Flights {

    private String lastDeparturesTime;
    private String lastArrivalsTime;

    private List<String> observedIDs;
    private Map<String, Flight> flightsMap;

    public static final String ARRIVALS = "A";
    public static final String DEPARTURES = "D";

    private final String TAG = "MEMORY";
    private Map<Integer, List<Flight>> alertsMap;
    public static final int NOWY = 0;
    public static final int AKTUALIZACJA = 1;
    public static final int USUNIETY = 2;
    private Logger log;

    public static Flights getInstance(Logger log) {
        if(INSTANCE==null) {
            INSTANCE = new Flights(log);
        }
        return INSTANCE;
    }

    public static Flights getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new Flights();
        }
        return INSTANCE;
    }
    private static Flights INSTANCE = null;
    private Flights(Logger log) {
        observedIDs = new ArrayList<>();
        flightsMap = new LinkedHashMap<>();
        alertsMap = new LinkedHashMap<>();
        this.log = log;
    }
    private Flights() {
       this(new Logger());
    }

    public List<Flight> getArrivals() {
        if(flightsMap!=null) {
            List<Flight> list = new ArrayList<>();
            for(Flight f : flightsMap.values()) {
                list.add(f);
            }
            return list;
        }
        return null;
    }

    public List<Flight> getDepartures() {
        if(flightsMap!=null) {
            List<Flight> list = new ArrayList<>();
            for(Flight f : flightsMap.values()) {
                list.add(f);
            }
            return list;
        }
        return null;
    }

    public String getLastArrivalsTime() {
        return lastArrivalsTime;
    }

    public void setLastArrivalsTime(String lastArrivalsTime) {
        this.lastArrivalsTime = lastArrivalsTime;
    }

    public String getLastDeparturesTime() {
        return lastDeparturesTime;
    }

    public void setLastDeparturesTime(String lastDeparturesTime) {
        this.lastDeparturesTime = lastDeparturesTime;
    }

    public void onProcessResponse(FlightsResponse fr, FlightsContract.LoadFlightsCallback callback) {
        if(fr!=null) {
            String timeA = fr.getArrivalsTime();
            String timeD = fr.getDeparturesTime();
            log.i(TAG, "onProcessResponse");
            alertsMap.clear();
            if((timeA!=null && !timeA.equals(lastArrivalsTime)) || (timeD!=null && !timeD.equals(lastDeparturesTime))) {
                List<Flight> listaAll = new ArrayList<>();
                if(timeA!=null && !timeA.equals(lastArrivalsTime)) {
                    setLastArrivalsTime(timeA);
                    String arrivalsString = fr.getArrivals();
                    if(arrivalsString!=null) {
                        List<Flight> decodedList = decodeList(ARRIVALS, arrivalsString);
                        if(decodedList!=null && decodedList.size()>0) {
                            listaAll.addAll(decodedList);
                        }
                    }
                }
                if(timeD!=null && !timeD.equals(lastDeparturesTime)) {
                    setLastDeparturesTime(timeD);
                    String departuresString = fr.getDepartures();
                    if(departuresString!=null) {
                        List<Flight> decodedList = decodeList(DEPARTURES, departuresString);
                        if(decodedList!=null && decodedList.size()>0) {
                            listaAll.addAll(decodedList);
                        }
                    }
                }
                if(checkForAlertsAndUpdateMap(listaAll)) {
                    callback.onAlertsLoaded(alertsMap);
                }
                if(flightsMap!=null && flightsMap.size()>0) {
                    callback.onFlightsLoaded();
                }
            } else {
                log.i(TAG, "no changes");
            }
        } else {
            callback.onError();
        }
    }

    private String makeId(Flight flight) {
        return flight.getType()+flight.getDestination()+flight.getFlight()+flight.getTime()+flight.isCurrentDay();
    }

    public boolean isFlightUpdated(Flight fresh, Flight old) {
        return old!=null && (!old.getExp_time().equals(fresh.getExp_time()) || !old.getStatus().equals(fresh.getStatus()));
    }

    public List<Flight> decodeList(String type, String res) {
        log.i(TAG, "decodeList: "+res);
        List<Flight> lista = new ArrayList<>();
        if(res!=null && res.length()>0) {
            String[] infos = res.split("\\|");
            for(String info : infos) {
                if(info!=null && info.length()>0) {
                    String[] parametry = info.split(";");
                    if(parametry.length == 7) {
                        String destination = parametry[DESTINATION].trim();
                        String flight = parametry[FLIGHT].trim();
                        String freighter = parametry[FREIGHTER].trim();
                        String time = parametry[TIME].trim();
                        String exp_time = parametry[TIME_EXP].trim();
                        String status = parametry[STATUS].trim();
                        if(status.length()==1 && Character.isWhitespace(status.charAt(0))) {
                            status = "";
                        }

                        boolean currentDay = Boolean.parseBoolean(parametry[CURRENT_DAY].trim());

                        Flight tmp = new Flight(type, destination, flight, freighter, time, exp_time, status, currentDay);
                        String id = makeId(tmp);
                        Flight lot = new Flight(id, tmp, observedIDs.contains(id));
                        lista.add(lot);
                    } else {
                        log.e(TAG, "Nieprawidlowa ilosc parametrow dla lotu "+info);
                    }
                } else {
                    log.e(TAG, "Pusty element odpowiedzi");
                }
            }
        } else {
            log.e(TAG, "Pusty element odpowiedzi");
        }
        return lista;
    }

    public boolean checkForAlertsAndUpdateMap(List<Flight> listaAll) {
        log.i(TAG, "checkForAlertsAndUpdateMap");
        Collection<Flight> oldList = flightsMap.values();
        boolean isNotInitial = (oldList!=null && oldList.size()>0);

        for(Flight old : oldList) {
            if(!listaAll.contains(old)) {
                addFlightToAlertMap(USUNIETY, old);
            }
        }
        List<Flight> toRemove = alertsMap.get(USUNIETY);
        if(toRemove!=null && toRemove.size()>0) {
            for(Flight f : toRemove) {
                String id = f.getId();
                flightsMap.remove(id);
                observedIDs.remove(id);
            }
        }
        for(Flight lot : listaAll) {
            String id = lot.getId();
            Flight result = flightsMap.get(id);
            if(isFlightUpdated(lot, result)) {
                addFlightToAlertMap(AKTUALIZACJA, lot);
            } else {
                if(isNotInitial) {
                    addFlightToAlertMap(NOWY, lot);
                }
            }
            flightsMap.put(id, lot);
        }
        log.i(TAG, "FlightsMap :"+flightsMap.toString());
        log.i(TAG, "AlertsMap: "+alertsMap.toString());
        return alertsMap!=null && alertsMap.size()>0;
    }

    private void addFlightToAlertMap(Integer typ, Flight flight) {
        List<Flight> lista = alertsMap.get(typ);
        if(lista==null) {
            lista = new LinkedList<>();
        }
        if(isFlightObserved(flight)) {
            log.i(TAG, "Alerts add "+typ+" "+flight);
            lista.add(flight);
        }
        alertsMap.put(typ, lista);
    }

    private boolean isFlightObserved(Flight clickedFlight) {
        if(clickedFlight!=null && observedIDs !=null) {
            String id = clickedFlight.getId();
            if(id!=null) {
                return observedIDs.contains(id);
            }
        }
        return false;
    }

    public void changeFlightObservationStatus(Flight clickedFlight) {
        log.i(TAG, "changeFlightObservationStatus");
        if(clickedFlight!=null && observedIDs !=null) {
            String id = clickedFlight.getId();
            boolean isObserved = observedIDs.contains(id);
            Flight flight = null;
            if(isObserved) {
                observedIDs.remove(id);
                flight = new Flight(id, clickedFlight, false);
            } else {
                observedIDs.add(id);
                flight = new Flight(id, clickedFlight, true);
            }

            if(flight!=null) {
                flightsMap.put(id, flight);
            }
            log.i(TAG, "Observed "+ observedIDs.toString());
        }
    }

    public Flight getFlightByID(String id) {
        if(id!=null) {
            return flightsMap.get(id);
        }
        return null;
    }
}
