package pl.poblocki.drawer.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import pl.poblocki.drawer.data.db.tables.AirlineTable;
import pl.poblocki.drawer.data.db.tables.AirportTable;
import pl.poblocki.drawer.data.db.tables.ConnectionTable;
import pl.poblocki.drawer.data.db.tables.DBTimeTable;
import pl.poblocki.drawer.model.Airport;

public class AirportDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Airport.db";

    public AirportDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(final SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Wersje SQLite'a starsze niż 3.6.19 nie obsługują kluczy obcych. Nie
            // robią tego także wersje skompilowane z opcją SQLITE_OMIT_FOREIGN_KEY
            // http://www.sqlite.org/foreignkeys.html#fk_enable
            //
            // Należy włączyć obsługę kluczy obcych, jeśli jest to możliwe
            // (i tak powinna być włączona, jednak warto się upewnić).
            db.execSQL("PRAGMA foreign_keys=ON;");

            // Sprawdzanie, czy klucze obce są włączone. Jeśli zapytanie nie zwraca danych, nie
            // należy nawet PRÓBOWAĆ korzystaćz takich kluczy.
            Cursor c = db.rawQuery("PRAGMA foreign_keys", null);
            if (c.moveToFirst()) {
                int result = c.getInt(0);
                Log.i("DB", "Obsługa kluczy obcych (1 = tak, 0 = nie): " + result);
            } else {
                // Można zastosować to podejście w metodzie onCreate i na przykład nie używać
                // kluczy obcych, jeśli nie są dostępne.
                Log.i("DB", "BRAK obsługi kluczy obcych");
                // W razie konieczności tu można przełączyć się na kod
                // oparty na wyzwalaczach.
            }
            if (!c.isClosed()) {
                c.close();
            }
        }
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        Log.i("DB", "Tworzenie bazy w metodzie onCreate klasy DataHelper.OpenHelper " + DATABASE_NAME);
        DBTimeTable.onCreate(db);
        AirlineTable.onCreate(db);
        AirportTable.onCreate(db);
        ConnectionTable.onCreate(db);
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        Log.i("DB", "SQLiteOpenHelper onUpgrade - oldVersion:" + oldVersion + " newVersion:" + newVersion);
        DBTimeTable.onUpgrade(db, oldVersion, newVersion);
        AirlineTable.onUpgrade(db, oldVersion, newVersion);
        AirportTable.onUpgrade(db, oldVersion, newVersion);
        ConnectionTable.onUpgrade(db, oldVersion, newVersion);
    }
}
