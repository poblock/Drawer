package pl.poblocki.drawer.data.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Iza on 16.06.2017.
 */

public class AirportTable {
    public static final String TABLE_NAME = "airports";

    public static class AirportColumns implements BaseColumns {
        public static final String CODE = "code";
        public static final String COUNTRY = "country";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + AirportTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(AirportColumns.CODE + " TEXT UNIQUE NOT NULL, ");
        sb.append(AirportColumns.COUNTRY + " TEXT NOT NULL, ");
        sb.append(AirportColumns.LATITUDE + " TEXT NOT NULL, ");
        sb.append(AirportColumns.LONGITUDE + " TEXT NOT NULL");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AirportTable.TABLE_NAME);
        AirportTable.onCreate(db);
    }
}
