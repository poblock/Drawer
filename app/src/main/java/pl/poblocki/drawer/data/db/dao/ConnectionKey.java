package pl.poblocki.drawer.data.db.dao;

/**
 * Created by krzysztof.poblocki on 2017-06-20.
 */

public class ConnectionKey {
    private final long airportId;
    private final long airlineId;

    public ConnectionKey(long airlineId, long airportId) {
        this.airlineId = airlineId;
        this.airportId = airportId;
    }

    public long getAirlineId() {
        return airlineId;
    }

    public long getAirportId() {
        return airportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectionKey that = (ConnectionKey) o;

        if (airportId != that.airportId) return false;
        return airlineId == that.airlineId;

    }

    @Override
    public int hashCode() {
        int result = (int) (airportId ^ (airportId >>> 32));
        result = 31 * result + (int) (airlineId ^ (airlineId >>> 32));
        return result;
    }
}
