package pl.poblocki.drawer.data.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Iza on 16.06.2017.
 */

public class AirlineTable {
    public static final String TABLE_NAME = "airlines";

    public static class AirlineColumns implements BaseColumns {
        public static final String CODE = "code";
        public static final String NAME = "name";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + AirlineTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(AirlineColumns.CODE + " TEXT UNIQUE NOT NULL, ");
        sb.append(AirlineColumns.NAME + " TEXT NOT NULL");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AirlineTable.TABLE_NAME);
        AirlineTable.onCreate(db);
    }
}
