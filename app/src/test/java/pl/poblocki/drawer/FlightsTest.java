package pl.poblocki.drawer;

/**
 * Created by krzysztof.poblocki on 2017-07-19.
 */

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;


import pl.poblocki.drawer.data.Flights;
import pl.poblocki.drawer.data.network.FlightsResponse;
import pl.poblocki.drawer.flights.FlightsContract;
import pl.poblocki.drawer.model.Flight;
import pl.poblocki.drawer.support.Logger;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlightsTest {

    static String[] arrivals = {
            "CPH;SK755;09:15;true;SK;09:25;LANDED|HEL;AY751;09:25;true;AY;09:28;DELAYED|BCN;W61706;00:40;false;W6;; |LPL;W61612;00:55;false;W6;; |",
            "CPH;SK755;09:15;true;SK;09:25;LANDED|HEL;AY751;09:25;true;AY;09:28;LANDED|BCN;W61706;00:40;false;W6;; |LPL;W61612;00:55;false;W6;; |", // HEL : zmiana statusu

            "CPH;SK755;09:15;true;SK;09:25;LANDED|HEL;AY751;09:25;true;AY;09:28;LANDED|BCN;W61706;00:40;false;W6;; |CPH;SK755;09:15;false;SK;; |",
            "CPH;SK755;09:15;true;SK;09:35;LANDED|HEL;AY751;09:25;true;AY;09:28;LANDED|BCN;W61706;00:40;false;W6;; |CPH;SK755;09:15;false;SK;; |",   // CPH currentDay : zmiana godziny

            "CPH;SK755;09:15;true;SK;09:25;LANDED|HEL;AY751;09:25;true;AY;09:28;LANDED|BCN;W61706;00:40;false;W6;; |",
            "CPH;SK755;09:15;true;SK;09:25;LANDED|HEL;AY751;09:25;true;AY;09:28;LANDED|BCN;W61706;00:40;false;W6;; |LPL;W61612;00:55;false;W6;; |", // LPL : nowy lot

            "CPH;SK755;09:15;true;SK;09:25;LANDED|HEL;AY751;09:25;true;AY;09:28;DELAYED|BCN;W61706;00:40;false;W6;; |LPL;W61612;00:55;false;W6;; |",
            "CPH;SK755;09:15;true;SK;09:25;LANDED|BCN;W61706;00:40;false;W6;; |LPL;W61612;00:55;false;W6;; |",                                      // HEL : usuniety lot

            "CPH;SK755;09:15;true;SK;09:25;LANDED|HEL;AY751;09:25;true;AY;09:28;LANDED|BCN;W61706;00:40;false;W6;; |CPH;SK755;09:15;false;SK;; |",
            "CPH;SK755;09:15;true;SK;09:35;CHANGED|HEL;AY751;09:25;true;AY;09:35;DELAYED|CPH;SK755;09:15;false;SK;; |LPL;W61612;00:55;false;W6;; |", // CPH time & status, HEL time & status, BCN deleted, LPL added
    };

    static final int CASE_1_BEFORE = 0;
    static final int CASE_1_AFTER = 1;
    static final int CASE_2_BEFORE = 2;
    static final int CASE_2_AFTER = 3;
    static final int CASE_3_BEFORE = 4;
    static final int CASE_3_AFTER = 5;
    static final int CASE_4_BEFORE = 6;
    static final int CASE_4_AFTER = 7;
    static final int CASE_5_BEFORE = 8;
    static final int CASE_5_AFTER = 9;

    @Mock
    Logger logger;

    @Mock
    FlightsContract.LoadFlightsCallback callback;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void decodingFlightsResponse() {
        FlightsResponse response = mock(FlightsResponse.class);
        when(response.getArrivals()).thenReturn(arrivals[CASE_1_BEFORE], arrivals[CASE_1_AFTER]);
        when(response.getDepartures()).thenReturn("");
        when(response.getArrivalsTime()).thenReturn("");
        when(response.getDeparturesTime()).thenReturn("");

//        List<Flight> alerts1 = Flights.getInstance(logger).decodeResponse(response);
//        assertNull(alerts1);
//        verify(response, times(1)).getArrivals();
//        assertNotNull(Flights.getInstance(logger).getArrivals());
//        assertNotEquals(0, Flights.getInstance(logger).getArrivals().size());
    }

    private List<Flight> checkTwoLists(int index, int index2) {
        List<Flight> old = Flights.getInstance(logger).decodeList(Flights.ARRIVALS,arrivals[index]);
        assertNotNull(old);
        List<Flight> fresh = Flights.getInstance(logger).decodeList(Flights.ARRIVALS, arrivals[index2]);
        assertNotNull(fresh);
        List<Flight> result = null;
        Flights.getInstance(logger).checkForAlertsAndUpdateMap(fresh);
        return result;
    }

    @Test
    public void checkingForAlerts() {
        List<Flight> result = checkTwoLists(CASE_1_BEFORE, CASE_1_AFTER);
//        assertEquals(1, result.size());
//        Flight flight = result.get(0);
//        assertEquals("HEL", flight.getDestination());
//        assertEquals("AY751", flight.getFlight());
//        assertEquals("09:25", flight.getTime());
//        assertEquals(true, flight.isCurrentDay());
//        assertEquals("AY", flight.getFreighter());
//        assertEquals("09:28", flight.getExp_time());
//        assertEquals("LANDED", flight.getStatus());
    }

    @Test
    public void checkingForAlerts2() {
        List<Flight> result = checkTwoLists(CASE_2_BEFORE, CASE_2_AFTER);
//        assertEquals(1, result.size());
//        Flight flight = result.get(0);
//        assertEquals("CPH", flight.getDestination());
//        assertEquals("09:35", flight.getExp_time());
//        assertEquals(true, flight.isCurrentDay());
    }

    @Test
    public void checkingForAlerts3() {
        List<Flight> alertsResult = checkTwoLists(CASE_3_BEFORE, CASE_3_AFTER);
//        assertEquals(1, alertsResult.size());
//        Flight flight = alertsResult.get(0);
//        assertEquals("LPL", flight.getDestination());
    }

    @Test
    public void checkingForAlerts4() {
        List<Flight> alertsResult = checkTwoLists(CASE_4_BEFORE, CASE_4_AFTER);
//        assertEquals(1, alertsResult.size());
//        Flight flight = alertsResult.get(0);
//        assertEquals("HEL", flight.getDestination());
    }

    @Test
    public void checkingForAlerts5() {
        List<Flight> alertsResult = checkTwoLists(CASE_5_BEFORE, CASE_5_AFTER);
//        assertEquals(4, alertsResult.size());
//        Flight flight = alertsResult.get(0);
//        assertEquals("HEL", flight.getDestination());
    }
}
