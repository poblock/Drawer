package pl.poblocki.drawer.data.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Iza on 16.06.2017.
 */

public class ConnectionTable {
    public static final String TABLE_NAME = "connections";

    public static class ConnectionColumns {
        public static final String AIRPORT_ID = "airport_id";
        public static final String AIRLINE_ID = "airline_id";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + ConnectionTable.TABLE_NAME + " (");
        sb.append(ConnectionColumns.AIRPORT_ID + " INTEGER NOT NULL, ");
        sb.append(ConnectionColumns.AIRLINE_ID + " INTEGER NOT NULL, ");
        sb.append("FOREIGN KEY(" + ConnectionColumns.AIRPORT_ID + ") REFERENCES " + AirportTable.TABLE_NAME + "("
                + BaseColumns._ID + "), ");
        sb.append("FOREIGN KEY(" + ConnectionColumns.AIRLINE_ID + ") REFERENCES " + AirlineTable.TABLE_NAME + "("
                + BaseColumns._ID + ") , ");
        sb.append("PRIMARY KEY ( " + ConnectionColumns.AIRPORT_ID + ", " + ConnectionColumns.AIRLINE_ID + ")");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConnectionTable.TABLE_NAME);
        ConnectionTable.onCreate(db);
    }
}
