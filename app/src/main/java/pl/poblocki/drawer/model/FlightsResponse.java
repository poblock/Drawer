package pl.poblocki.drawer.model;

import java.util.List;

/**
 * Created by Iza on 15.06.2017.
 */

public class FlightsResponse {
    private String arrivals;
    private String departures;
    private String arrivalsTime;
    private String departuresTime;

    public FlightsResponse(String arrivals, String arrivalsTime, String departures, String departuresTime) {
        this.arrivals = arrivals;
        this.arrivalsTime = arrivalsTime;
        this.departures = departures;
        this.departuresTime = departuresTime;
    }

    public String getArrivals() {
        return arrivals;
    }

    public void setArrivals(String arrivals) {
        this.arrivals = arrivals;
    }

    public String getArrivalsTime() {
        return arrivalsTime;
    }

    public void setArrivalsTime(String arrivalsTime) {
        this.arrivalsTime = arrivalsTime;
    }

    public String getDepartures() {
        return departures;
    }

    public void setDepartures(String departures) {
        this.departures = departures;
    }

    public String getDeparturesTime() {
        return departuresTime;
    }

    public void setDeparturesTime(String departuresTime) {
        this.departuresTime = departuresTime;
    }


    @Override
    public String toString() {
        return "FlightsResponse{" +
                "arrivals=" + arrivals +
                ", departures=" + departures +
                ", arrivalsTime='" + arrivalsTime + '\'' +
                ", departuresTime='" + departuresTime + '\'' +
                '}';
    }
}
