package pl.poblocki.drawer.model;

/**
 * Created by krzysztof.poblocki on 2017-06-07.
 */

public class Airline {
    private long id;
    private String airlineName;
    private String airlineCode;

    public Airline() {

    }

    public Airline(long id, String airlineCode, String airlineName) {
        this.id = id;
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
    }

    public Airline(String airlineCode, String airlineName) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }


    @Override
    public String toString() {
        return "Airline{" +
                "airlineCode='" + airlineCode + '\'' +
                ", airlineName='" + airlineName + '\'' +
                '}';
    }
}
