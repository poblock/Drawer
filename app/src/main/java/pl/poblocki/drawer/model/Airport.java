package pl.poblocki.drawer.model;

/**
 * Created by krzysztof.poblocki on 2017-06-07.
 */

public class Airport {
    private long id;
    private String airportCode;
    private String airportName;
    private String countryCode;
    private String latitude;
    private String longitude;

    public Airport() {

    }

    public Airport(String airportCode, String airportCountry, String latitude, String longitude) {
        setCode(airportCode);
        setCountry(airportCountry);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Airport(String airportName, String airportCode, String airportCountry, String latitude, String longitude) {
        setName(airportName);
        setCode(airportCode);
        setCountry(airportCountry);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return airportCode;
    }

    public void setCode(String code) {
        this.airportCode = code;
    }

    public void setName(String name) {
        this.airportName = name;
    }

    public String getName() {
        return airportName;
    }

    public String getCountry() {
        return countryCode;
    }

    public void setCountry(String country) {
        this.countryCode = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "Airport{" +
                "airportCode='" + airportCode + '\'' +
                ", airportName='" + airportName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
