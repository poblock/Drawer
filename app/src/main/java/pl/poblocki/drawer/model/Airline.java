package pl.poblocki.drawer.model;

/**
 * Created by krzysztof.poblocki on 2017-06-07.
 */

public class Airline {
    private String airlineName;
    private String airlineCode;

    public Airline(String airlineCode, String airlineName) {
        this.airlineCode = airlineCode;
        this.airlineName = airlineName;
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
