package pl.poblocki.drawer.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import pl.poblocki.drawer.data.db.tables.AirportTable;
import pl.poblocki.drawer.model.Airport;

/**
 * Created by krzysztof.poblocki on 2017-06-20.
 */

public class AirportDao {
    private static final String INSERT =
            "insert into " + AirportTable.TABLE_NAME
                    + "(" + AirportTable.AirportColumns.CODE + ", " + AirportTable.AirportColumns.COUNTRY + ", "
                    + AirportTable.AirportColumns.LATITUDE + ", " + AirportTable.AirportColumns.LONGITUDE + ") values (?, ?, ?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public AirportDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(AirportDao.INSERT);
    }

    public long save(Airport airport) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, airport.getCode());
        insertStatement.bindString(2, airport.getCountry());
        insertStatement.bindString(3, airport.getLongitude());
        insertStatement.bindString(4, airport.getLatitude());
        return insertStatement.executeInsert();
    }

    public Airport get(long id) {
        Airport airport = null;
        Cursor c =
                db.query(AirportTable.TABLE_NAME, new String[] { BaseColumns._ID, AirportTable.AirportColumns.CODE,
                        AirportTable.AirportColumns.COUNTRY, AirportTable.AirportColumns.LATITUDE, AirportTable.AirportColumns.LONGITUDE},
                        BaseColumns._ID + " = ?", new String[] { String.valueOf(id) }, null, null, null, "1");
        if (c.moveToFirst()) {
            airport = buildAirportFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return airport;
    }

    public void deleteAll() {
        db.delete(AirportTable.TABLE_NAME, null, null);
    }

    public List<Airport> getAll() {
        List<Airport> list = new ArrayList<>();
        Cursor c =
                db.query(AirportTable.TABLE_NAME, new String[] { BaseColumns._ID, AirportTable.AirportColumns.CODE,
                                AirportTable.AirportColumns.COUNTRY, AirportTable.AirportColumns.LATITUDE, AirportTable.AirportColumns.LONGITUDE}, null,
                        null, null, null, AirportTable.AirportColumns.CODE, null);
        if (c.moveToFirst()) {
            do {
                Airport airport = buildAirportFromCursor(c);
                if (airport != null) {
                    list.add(airport);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

    public Airport find(String code) {
        long airportID = 0L;
        String sql = "select _id from " + AirportTable.TABLE_NAME + " where upper(" + AirportTable.AirportColumns.CODE + ") = ? limit 1";
        Cursor c = db.rawQuery(sql, new String[] { code.toUpperCase() });
        if (c.moveToFirst()) {
            airportID = c.getLong(0);
        }
        if (!c.isClosed()) {
            c.close();
        }
        // Następne zapytanie, co wymaga ponownego przesyłania danych. Jest to
        // koszt, na który decydujemy się z uwagi na małą ilość danych.
        return this.get(airportID);
    }

    private Airport buildAirportFromCursor(Cursor c) {
        Airport airport = null;
        if (c != null) {
            airport = new Airport();
            airport.setId(c.getLong(0));
            airport.setCode(c.getString(1));
            airport.setCountry(c.getString(2));
            airport.setLatitude(c.getString(3));
            airport.setLongitude(c.getString(4));
        }
        return airport;
    }
}
