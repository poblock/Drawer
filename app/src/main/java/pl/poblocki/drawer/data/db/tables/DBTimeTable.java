package pl.poblocki.drawer.data.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Iza on 16.06.2017.
 */

public class DBTimeTable {
    public static final String TABLE_NAME = "time";

    public static class DBTimeColumns implements BaseColumns {
        public static final String NAME = "name";
        public static final String TIME = "time";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + DBTimeTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(DBTimeColumns.NAME + " TEXT UNIQUE NOT NULL, ");
        sb.append(DBTimeColumns.TIME + " TEXT NOT NULL");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBTimeTable.TABLE_NAME);
        DBTimeTable.onCreate(db);
    }
}
