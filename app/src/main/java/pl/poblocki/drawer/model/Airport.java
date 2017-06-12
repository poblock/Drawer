package pl.poblocki.drawer.model;

/**
 * Created by krzysztof.poblocki on 2017-06-07.
 */

public class Airport {
    private String code;
    private String name;
    private String country;
    private String latitude;
    private String longitude;

    public Airport(String name, String code, String country, String latitude, String longitude) {
        setName(name);
        setCode(code);
        setCountry(country);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
}
