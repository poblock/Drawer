package pl.poblocki.drawer.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IBM on 2016-12-10.
 */

public class Content {

    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
    private static final int COUNT = 50;

    public static List<DummyItem> makeList(int ID) {
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i*ID));
        }
        return ITEMS;
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;
//        public final String destination;
//        public final String flight;
//        public final String freighter;
//        public final String time;
//        public final String exp_time;
//        public final String remarks;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }


}
