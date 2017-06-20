package pl.poblocki.drawer.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import pl.poblocki.drawer.data.db.tables.DBTimeTable;
import pl.poblocki.drawer.model.DBTime;

/**
 * Created by krzysztof.poblocki on 2017-06-20.
 */

public class DBTimeDao {
    private static final String INSERT =
            "insert into " + DBTimeTable.TABLE_NAME
                    + "(" + DBTimeTable.DBTimeColumns.NAME + ", " + DBTimeTable.DBTimeColumns.TIME + ") values (?, ?)";

    private SQLiteDatabase db;
    private SQLiteStatement insertStatement;

    public DBTimeDao(SQLiteDatabase db) {
        this.db = db;
        insertStatement = db.compileStatement(DBTimeDao.INSERT);
    }

    public long save(DBTime dbTime) {
        insertStatement.clearBindings();
        insertStatement.bindString(1, dbTime.getDbName());
        insertStatement.bindString(2, dbTime.getDbTime());
        return insertStatement.executeInsert();
    }

    public DBTime get(long id) {
        DBTime dbTime = null;
        Cursor c =
                db.query(DBTimeTable.TABLE_NAME, new String[] { BaseColumns._ID, DBTimeTable.DBTimeColumns.NAME,
                                DBTimeTable.DBTimeColumns.TIME},
                        BaseColumns._ID + " = ?", new String[] { String.valueOf(id) }, null, null, null, "1");
        if (c.moveToFirst()) {
            dbTime = buildDBTimeFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return dbTime;
    }

    public List<DBTime> getAll() {
        List<DBTime> list = new ArrayList<>();
        Cursor c =
                db.query(DBTimeTable.TABLE_NAME, new String[] { BaseColumns._ID, DBTimeTable.DBTimeColumns.NAME,
                                DBTimeTable.DBTimeColumns.TIME}, null,
                        null, null, null, DBTimeTable.DBTimeColumns.NAME, null);
        if (c.moveToFirst()) {
            do {
                DBTime dbTime = buildDBTimeFromCursor(c);
                if (dbTime != null) {
                    list.add(dbTime);
                }
            } while (c.moveToNext());
        }
        if (!c.isClosed()) {
            c.close();
        }
        return list;
    }

    public DBTime find(String name) {
        long dbTimeID = 0L;
        String sql = "select _id from " + DBTimeTable.TABLE_NAME + " where upper(" + DBTimeTable.DBTimeColumns.NAME + ") = ? limit 1";
        Cursor c = db.rawQuery(sql, new String[] { name.toUpperCase() });
        if (c.moveToFirst()) {
            dbTimeID = c.getLong(0);
        }
        if (!c.isClosed()) {
            c.close();
        }
        // Następne zapytanie, co wymaga ponownego przesyłania danych. Jest to
        // koszt, na który decydujemy się z uwagi na małą ilość danych.
        return get(dbTimeID);
    }

    private DBTime buildDBTimeFromCursor(Cursor c) {
        DBTime dbTime = null;
        if (c != null) {
            dbTime = new DBTime();
            dbTime.setId(c.getLong(0));
            dbTime.setDbName(c.getString(1));
            dbTime.setDbTime(c.getString(2));
        }
        return dbTime;
    }
}
