package pl.poblocki.drawer.list;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by IBM on 2016-12-10.
 */

public class Content {

    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
    private static final int COUNT = 50;

    private static String[] airlines = {"Wizzair", "Ryanair", "SAS", "LOT", "KLM", "Lufthansa", "Norwegian", "UIA", "Air Berlin", "Finnair",
    "Travel Service", "7 Islands", "Blue Bird", "Ecco", "Exim", "Grecos", "Itaka", "Matimpex", "Neckermann", "Rainbow", "Small Planet",
    "SunFun", "TUI", "Wezyr", "Wizz Tours", "Sprint Air", "AlMasria", "Corendon"};
    private static String[] destinations = {"WARSZAWA","MONACHIUM","SZTOKHOLM ARLANDA","LONDYN LUTON","LONDYN STANSTED","MEDIOLAN",
    "EINDHOVEN","TURKU","KOPENHAGA","TENERIFE SOUTH","DONCASTER SHEFFIELD", "FUERTEVENTURA", "RADOM", "GRAN CANARIA"};
    private static String[] remarks = {"OPÓŹNIONY 10:35", "WYLĄDOWAŁ", "", "OCZEKIWANY 12:48", "OPÓŹNIONY 16:30/VOUCHERY GATE 21", "DO WYJŚCIA/OPÓŹNIONY 14:55"};
    private static String[] codes = {"LO 3835", "LH 1642", "W6 1602", "FR 2374"};
    private static String[] times = {"09:45", "10:35", "11:25"," 11:05", "12:48", "13:46"};

    public static List<DummyItem> makeList(boolean isNextDay) {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(isNextDay));
        }
        return ITEMS;
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.flight, item);
    }

    private static DummyItem createDummyItem(boolean isNextDay) {
        Random r = new Random();
        return new DummyItem(
                destinations[r.nextInt(destinations.length)],
                codes[r.nextInt(codes.length)],
                airlines[r.nextInt(airlines.length)],
                times[r.nextInt(times.length)],
                times[r.nextInt(times.length)],
                remarks[r.nextInt(remarks.length)],
                isNextDay);
    }

    public static class DummyItem {
        public final String destination;
        public final String flight;
        public final String freighter;
        public final String time;
        public final String exp_time;
        public final String remarks;
        public final boolean nextDay;

        public DummyItem(String destination, String flight, String freighter, String time, String exp_time, String remarks, boolean nextDay) {
            this.destination = destination;
            this.flight = flight;
            this.freighter = freighter;
            this.time = time;
            this.exp_time = exp_time;
            this.remarks = remarks;
            this.nextDay = nextDay;
        }

        @Override
        public String toString() {
            return "DummyItem{" +
                    "destination='" + destination + '\'' +
                    ", flight='" + flight + '\'' +
                    ", freighter='" + freighter + '\'' +
                    ", time='" + time + '\'' +
                    ", exp_time='" + exp_time + '\'' +
                    ", remarks='" + remarks + '\'' +
                    ", nextDay=" + nextDay +
                    '}';
        }
    }


}
