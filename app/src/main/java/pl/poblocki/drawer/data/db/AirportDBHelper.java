package pl.poblocki.drawer.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class AirportDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Airport.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    public static abstract class AirportEntry implements BaseColumns {
        public static final String TABLE_NAME = "airports";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
    }

    public static abstract class TimeEntry implements BaseColumns {
        public static final String TABLE_NAME = "time";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TIME = "time";
    }

    public static abstract class CountryEntry implements BaseColumns {
        public static final String TABLE_NAME = "countries";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CODE = "code";
    }

    public static abstract class AirlineEntry implements BaseColumns {
        public static final String TABLE_NAME = "airlines";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CODE = "code";
        public static final String COLUMN_NAME_IMAGE = "image";
    }

    public static abstract class FlightEntry implements BaseColumns {
        public static final String TABLE_NAME = "flights";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_AIRPORT_ID = "airport_id";
        public static final String COLUMN_NAME_AIRLINE_ID = "airline_id";
    }

    public static String[] selectAllDBTime  = {
            TimeEntry.COLUMN_NAME_ID,
            TimeEntry.COLUMN_NAME_NAME,
            TimeEntry.COLUMN_NAME_TIME
    };

    public static String[] selectAllAirports  = {
            AirportEntry.COLUMN_NAME_ID,
            AirportEntry.COLUMN_NAME_NAME,
            AirportEntry.COLUMN_NAME_CODE,
            AirportEntry.COLUMN_NAME_COUNTRY,
            AirportEntry.COLUMN_NAME_LATITUDE,
            AirportEntry.COLUMN_NAME_LONGITUDE
    };

    private static final String SQL_CREATE_AIRPORT_ENTRIES =
            "CREATE TABLE " + AirportEntry.TABLE_NAME + " (" +
                    AirportEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    AirportEntry.COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP +
                    AirportEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    AirportEntry.COLUMN_NAME_CODE + TEXT_TYPE + COMMA_SEP +
                    AirportEntry.COLUMN_NAME_COUNTRY + TEXT_TYPE + COMMA_SEP +
                    AirportEntry.COLUMN_NAME_LATITUDE + TEXT_TYPE + COMMA_SEP +
                    AirportEntry.COLUMN_NAME_LONGITUDE + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_DBTIME_ENTRIES =
            "CREATE TABLE " + TimeEntry.TABLE_NAME + " (" +
                    TimeEntry._ID + TEXT_TYPE + " PRIMARY KEY," +
                    TimeEntry.COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP +
                    TimeEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    TimeEntry.COLUMN_NAME_TIME + TEXT_TYPE + COMMA_SEP +
                    " )";
    
    public AirportDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_AIRPORT_ENTRIES);
        db.execSQL(SQL_CREATE_DBTIME_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
