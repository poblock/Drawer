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

    private static String[] airlines = {"Wizzair", "Ryanair", "SAS", "LOT", "KLM", "Lufthansa", "Norwegian", "UIA", "Air Berlin", "Finnair"};
    private static String[] destinations = {"WARSZAWA","MONACHIUM","SZTOKHOLM ARLANDA","LONDYN LUTON","LONDYN STANSTED","MEDIOLAN",
    "EINDHOVEN","TURKU","KOPENHAGA","TENERIFE SOUTH","DONCASTER SHEFFIELD"};
    private static String[] remarks = {"OPÓŹNIONY 10:35", "WYLĄDOWAŁ", "", "OCZEKIWANY 12:48"};

    public static List<DummyItem> makeList(int ID) {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i*ID));
        }
        return ITEMS;
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.flight, item);
    }

    private static DummyItem createDummyItem(int position) {
        int reszta = position % 11;
        Random r = new Random();
        DummyItem item = null;
        switch (reszta) {
            case 0 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"LO 3835",airlines[reszta],"09:45","10:35",remarks[r.nextInt(remarks.length)]);
                break;
            case 1 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"LH 1642",airlines[reszta],"12:40","",remarks[r.nextInt(remarks.length)]);
                break;
            case 2 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"W6 1602",airlines[reszta],"11:25","11:05",remarks[r.nextInt(remarks.length)]);
                break;
            case 3 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"W6 1602",airlines[reszta],"11:25","13:46",remarks[r.nextInt(remarks.length)]);
                break;
            case 4 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"FR 2374",airlines[reszta],"11:25","12:48",remarks[r.nextInt(remarks.length)]);
                break;
            case 5 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"FR 2374",airlines[reszta],"11:25","12:48",remarks[r.nextInt(remarks.length)]);
                break;
            case 6 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"FR 2374",airlines[reszta],"11:25","12:48",remarks[r.nextInt(remarks.length)]);
                break;
            case 7 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"FR 2374",airlines[reszta],"11:25","12:48",remarks[r.nextInt(remarks.length)]);
                break;
            case 8 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"FR 2374",airlines[reszta],"11:25","12:48",remarks[r.nextInt(remarks.length)]);
                break;
            case 9 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"FR 2374",airlines[reszta],"11:25","12:48",remarks[r.nextInt(remarks.length)]);
                break;
            case 10 :
                item = new DummyItem(destinations[r.nextInt(destinations.length)],"FR 2374","Ryanair","11:25","12:48",remarks[r.nextInt(remarks.length)]);
                break;
        }
        return item;
    }

    public static class DummyItem {
        public final String destination;
        public final String flight;
        public final String freighter;
        public final String time;
        public final String exp_time;
        public final String remarks;

        public DummyItem(String destination, String flight, String freighter, String time, String exp_time, String remarks) {
            this.destination = destination;
            this.flight = flight;
            this.freighter = freighter;
            this.time = time;
            this.exp_time = exp_time;
            this.remarks = remarks;
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
                    '}';
        }
    }


}
