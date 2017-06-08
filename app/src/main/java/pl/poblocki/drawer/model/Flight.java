package pl.poblocki.drawer.model;

/**
 * Created by krzysztof.poblocki on 2017-06-07.
 */

public class Flight {
    private String destination;
    private String flight;
    private String freighter;
    private String time;
    private String exp_time;
    private String status;
    private boolean currentDay;

    public Flight() {
    }

    public Flight(String destination, String flight, String freighter, String time, String exp_time, String remarks, boolean nextDay) {
        this.destination = destination;
        this.exp_time = exp_time;
        this.flight = flight;
        this.freighter = freighter;
        this.currentDay = nextDay;
        this.status = remarks;
        this.time = time;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getExp_time() {
        return exp_time;
    }

    public void setExp_time(String exp_time) {
        this.exp_time = exp_time;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getFreighter() {
        return freighter;
    }

    public void setFreighter(String freighter) {
        this.freighter = freighter;
    }

    public boolean isCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(boolean currentDay) {
        this.currentDay = currentDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight1 = (Flight) o;

        if (currentDay != flight1.currentDay) return false;
        if (destination != null ? !destination.equals(flight1.destination) : flight1.destination != null)
            return false;
        if (flight != null ? !flight.equals(flight1.flight) : flight1.flight != null) return false;
        if (freighter != null ? !freighter.equals(flight1.freighter) : flight1.freighter != null)
            return false;
        if (time != null ? !time.equals(flight1.time) : flight1.time != null) return false;
        if (exp_time != null ? !exp_time.equals(flight1.exp_time) : flight1.exp_time != null)
            return false;
        return status != null ? status.equals(flight1.status) : flight1.status == null;

    }

    @Override
    public int hashCode() {
        int result = destination != null ? destination.hashCode() : 0;
        result = 31 * result + (flight != null ? flight.hashCode() : 0);
        result = 31 * result + (freighter != null ? freighter.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (exp_time != null ? exp_time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (currentDay ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "destination='" + destination + '\'' +
                ", flight='" + flight + '\'' +
                ", freighter='" + freighter + '\'' +
                ", time='" + time + '\'' +
                ", exp_time='" + exp_time + '\'' +
                ", status='" + status + '\'' +
                ", currentDay=" + currentDay +
                '}';
    }
}
