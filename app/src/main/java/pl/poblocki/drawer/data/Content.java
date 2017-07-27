package pl.poblocki.drawer.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pl.poblocki.drawer.model.Flight;

/**
 * Created by IBM on 2016-12-10.
 */

public class Content {

    public static List<Flight> makeMockList(boolean isNextDay) {
        Random r = new Random();
        int MOCK_COUNT = 50;
        String[] airlines = {"Wizzair", "Ryanair", "SAS", "LOT", "KLM", "Lufthansa", "Norwegian", "UIA", "Air Berlin", "Finnair",
                "Travel Service", "7 Islands", "Blue Bird", "Ecco", "Exim", "Grecos", "Itaka", "Matimpex", "Neckermann", "Rainbow", "Small Planet",
                "SunFun", "TUI", "Wezyr", "Wizz Tours", "Sprint Air", "AlMasria", "Corendon", "Adria Airways", "Enter Air"};
        String[] destinations = {"WARSZAWA","MONACHIUM","SZTOKHOLM ARLANDA","LONDYN LUTON","LONDYN STANSTED","MEDIOLAN",
                "EINDHOVEN","TURKU","KOPENHAGA","TENERIFE SOUTH","DONCASTER SHEFFIELD", "FUERTEVENTURA", "RADOM", "GRAN CANARIA"};
        String[] remarks = {"OPÓŹNIONY 10:35", "WYLĄDOWAŁ", "", "OCZEKIWANY 12:48", "OPÓŹNIONY 16:30/VOUCHERY GATE 21", "DO WYJŚCIA/OPÓŹNIONY 14:55"};
        String[] times = {"09:45", "10:35", "11:25"," 11:05", "12:48", "13:46"};
        String[] codes = {"LO 3835", "LH 1642", "W6 1602", "FR 2374"};

        List<Flight> mockItems = new ArrayList<>();
        for (int i = 1; i <= MOCK_COUNT; i++) {
            Flight item = new Flight("",
                    destinations[r.nextInt(destinations.length)],
                    codes[r.nextInt(codes.length)],
                    airlines[r.nextInt(airlines.length)],
                    times[r.nextInt(times.length)],
                    times[r.nextInt(times.length)],
                    remarks[r.nextInt(remarks.length)],
                    isNextDay);
            mockItems.add(item);
        }
        return mockItems;
    }
}
