package pl.poblocki.drawer.model;

import com.google.common.base.Objects;

/**
 * Created by krzysztof.poblocki on 2017-06-07.
 */

public class Flight {

    public static final int DESTINATION = 0;
    public static final int FLIGHT = 1;
    public static final int TIME = 2;
    public static final int CURRENT_DAY = 3;
    public static final int FREIGHTER = 4;
    public static final int TIME_EXP = 5;
    public static final int STATUS = 6;

    private final String destination;
    private final String flight;
    private final String freighter;
    private final String time;
    private final String exp_time;
    private final String status;
    private final boolean currentDay;
    private final boolean observed;
    private final String type;
    private final String id;

    public Flight(String id, String type, String destination, String flight, String freighter, String time, String exp_time, String remarks, boolean currentDay, boolean observed) {
        this.id = id;
        this.type = type;
        this.destination = destination;
        this.exp_time = exp_time;
        this.flight = flight;
        this.freighter = freighter;
        this.currentDay = currentDay;
        this.status = remarks;
        this.time = time;
        this.observed = observed;
    }

    public Flight(String id, Flight flight, boolean observed) {
        this(id, flight.getType(), flight.getDestination(), flight.getFlight(), flight.getFreighter(),
                flight.getTime(), flight.getExp_time(), flight.getStatus(), flight.isCurrentDay(), observed);
    }

    public Flight(String type, String destination, String flight, String freighter, String time, String exp_time, String remarks, boolean currentDay) {
        this.id = "";
        this.type = type;
        this.destination = destination;
        this.exp_time = exp_time;
        this.flight = flight;
        this.freighter = freighter;
        this.currentDay = currentDay;
        this.status = remarks;
        this.time = time;
        this.observed = false;
    }

    public String getType() {
        return type;
    }

    public String getDestination() {
        return destination;
    }

    public String getExp_time() {
        return exp_time;
    }

    public String getFlight() {
        return flight;
    }

    public String getFreighter() {
        return freighter;
    }

    public boolean isCurrentDay() {
        return currentDay;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public boolean isObserved() {
        return observed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight1 = (Flight) o;
        return Objects.equal(type, flight1.type) && Objects.equal(currentDay, flight1.currentDay) &&
                Objects.equal(destination, flight1.destination) &&
                Objects.equal(flight, flight1.flight) &&
                Objects.equal(time, flight1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type, currentDay, destination, flight, time);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "currentDay=" + currentDay +
                ", destination='" + destination + '\'' +
                ", flight='" + flight + '\'' +
                ", freighter='" + freighter + '\'' +
                ", time='" + time + '\'' +
                ", exp_time='" + exp_time + '\'' +
                ", status='" + status + '\'' +
                ", observed=" + observed +
                '}';
    }

    public String getId() {
        return id;
    }
}
