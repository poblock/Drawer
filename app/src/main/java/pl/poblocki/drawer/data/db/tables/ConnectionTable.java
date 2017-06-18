package pl.poblocki.drawer.data.db.tables;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by Iza on 16.06.2017.
 */

public class ConnectionTable {
    public static final String TABLE_NAME = "movie_category";

    public static class MovieCategoryColumns {
        public static final String MOVIE_ID = "movie_id";
        public static final String CATEGORY_ID = "category_id";
    }

    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE " + ConnectionTable.TABLE_NAME + " (");

        sb.append(MovieCategoryColumns.MOVIE_ID + " INTEGER NOT NULL, ");
        sb.append(MovieCategoryColumns.CATEGORY_ID + " INTEGER NOT NULL, ");
        sb.append("FOREIGN KEY(" + MovieCategoryColumns.MOVIE_ID + ") REFERENCES " + AirportTable.TABLE_NAME + "("
                + BaseColumns._ID + "), ");
        sb.append("FOREIGN KEY(" + MovieCategoryColumns.CATEGORY_ID + ") REFERENCES " + AirlineTable.TABLE_NAME + "("
                + BaseColumns._ID + ") , ");
        sb.append("PRIMARY KEY ( " + MovieCategoryColumns.MOVIE_ID + ", " + MovieCategoryColumns.CATEGORY_ID + ")");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConnectionTable.TABLE_NAME);
        ConnectionTable.onCreate(db);
    }
}
