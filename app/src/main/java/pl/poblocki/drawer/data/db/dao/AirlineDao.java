package pl.poblocki.drawer.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import pl.poblocki.drawer.data.db.tables.AirlineTable;
import pl.poblocki.drawer.model.Airline;

/**
 * Created by krzysztof.poblocki on 2017-06-20.
 */

public class AirlineDao {
    private static final String INSERT =
            "insert into " + AirlineTable.TABLE_NAME
                    + "(" + AirlineTable.AirlineColumns.CODE + ", " + AirlineTable.AirlineColumns.NAME + ") values (?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public AirlineDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(AirlineDao.INSERT);
    }

    public long save(Airline airline) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, airline.getAirlineCode());
        insertStatement.bindString(2, airline.getAirlineName());
        return insertStatement.executeInsert();
    }

    public Airline get(long id) {
        Airline airline = null;
        Cursor c =
                db.query(AirlineTable.TABLE_NAME, new String[] { BaseColumns._ID, AirlineTable.AirlineColumns.CODE,
                                AirlineTable.AirlineColumns.NAME},
                        BaseColumns._ID + " = ?", new String[] { String.valueOf(id) }, null, null, null, "1");
        if (c.moveToFirst()) {
            airline = this.buildAirlineFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return airline;
    }

    public List<Airline> getAll() {
        List<Airline> list = new ArrayList<>();
        Cursor c =
                db.query(AirlineTable.TABLE_NAME, new String[] { BaseColumns._ID, AirlineTable.AirlineColumns.CODE,
                                AirlineTable.AirlineColumns.NAME}, null,
                        null, null, null, AirlineTable.AirlineColumns.CODE, null);
        if (c.moveToFirst()) {
            do {
                Airline airline = this.buildAirlineFromCursor(c);
                if (airline != null) {
                    list.add(airline);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

    public Airline find(String code) {
        long airlineID = 0L;
        String sql = "select _id from " + AirlineTable.TABLE_NAME + " where upper(" + AirlineTable.AirlineColumns.CODE + ") = ? limit 1";
        Cursor c = db.rawQuery(sql, new String[] { code.toUpperCase() });
        if (c.moveToFirst()) {
            airlineID = c.getLong(0);
        }
        if (!c.isClosed()) {
            c.close();
        }
        // Następne zapytanie, co wymaga ponownego przesyłania danych. Jest to
        // koszt, na który decydujemy się z uwagi na małą ilość danych.
        return this.get(airlineID);
    }

    private Airline buildAirlineFromCursor(Cursor c) {
        Airline airline = null;
        if (c != null) {
            airline = new Airline();
            airline.setId(c.getLong(0));
            airline.setAirlineCode(c.getString(1));
            airline.setAirlineName(c.getString(2));
        }
        return airline;
    }
}
