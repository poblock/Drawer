package pl.poblocki.drawer.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import pl.poblocki.drawer.data.db.tables.AirlineTable;
import pl.poblocki.drawer.data.db.tables.ConnectionTable;
import pl.poblocki.drawer.model.Airline;

/**
 * Created by krzysztof.poblocki on 2017-06-20.
 */

public class ConnectionDao {
    private static final String INSERT =
            "insert into " + ConnectionTable.TABLE_NAME + "(" + ConnectionTable.ConnectionColumns.AIRPORT_ID + ", "
                    + ConnectionTable.ConnectionColumns.AIRLINE_ID + ") values (?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public ConnectionDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(ConnectionDao.INSERT);
    }

    public long save(ConnectionKey entity) {
        insertStatement.clearBindings();
        insertStatement.bindLong(1, entity.getAirportId());
        insertStatement.bindLong(2, entity.getAirlineId());
        return insertStatement.executeInsert();
    }

    public void delete(ConnectionKey key) {
        if ((key.getAirportId() > 0) && (key.getAirlineId() > 0)) {
            db.delete(ConnectionTable.TABLE_NAME, ConnectionTable.ConnectionColumns.AIRPORT_ID + " = ? and "
                    + ConnectionTable.ConnectionColumns.AIRLINE_ID + " = ?", new String[] { String.valueOf(key.getAirportId()),
                    String.valueOf(key.getAirlineId()) });
        }
    }

    public boolean exists(ConnectionKey key) {
        boolean result = false;
        Cursor c =
                db.query(ConnectionTable.TABLE_NAME, new String[] { ConnectionTable.ConnectionColumns.AIRPORT_ID,
                        ConnectionTable.ConnectionColumns.AIRLINE_ID }, ConnectionTable.ConnectionColumns.AIRPORT_ID + " = ? and "
                        + ConnectionTable.ConnectionColumns.AIRLINE_ID  + " = ?", new String[] { String.valueOf(key.getAirportId()),
                        String.valueOf(key.getAirlineId()) }, null, null, null, "1");
        if (c.moveToFirst()) {
            result = true; // Jesli uzyesz tylko "return true", kursor nie zostanie zamkniety
        }
        if (!c.isClosed()) {
            c.close();
        }
        return result;
    }

    public List<Airline> getAirlines(long airportID) {
        List<Airline> list = new ArrayList<>();

        String sql =
                "select " + ConnectionTable.ConnectionColumns.AIRLINE_ID + ", " + AirlineTable.AirlineColumns.CODE +", " + AirlineTable.AirlineColumns.NAME + " from "
                        + ConnectionTable.TABLE_NAME + ", " + AirlineTable.TABLE_NAME + " where "
                        + ConnectionTable.ConnectionColumns.AIRPORT_ID + " = ? and " + ConnectionTable.ConnectionColumns.AIRLINE_ID + " = "
                        + BaseColumns._ID;
        Cursor c = db.rawQuery(sql, new String[] { String.valueOf(airportID) });
        if (c.moveToFirst()) {
            do {
                Airline airline = new Airline(c.getLong(0), c.getString(1), c.getString(2));
                list.add(airline);
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }
}
