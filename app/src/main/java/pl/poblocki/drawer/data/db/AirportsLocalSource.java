package pl.poblocki.drawer.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pl.poblocki.drawer.model.Airport;

public class AirportsLocalSource {

    private static AirportsLocalSource INSTANCE = null;
    private AirportDBHelper mDbHelper;
    private AirportsLocalSource(Context context) {
        mDbHelper = new AirportDBHelper(context);
    }
    public static AirportsLocalSource getInstance(Context context) {
        if(INSTANCE==null) {
            INSTANCE = new AirportsLocalSource(context);
        }
        return INSTANCE;
    }

    public List<Airport> getAirports() {
        List<Airport> miasta = new ArrayList<Airport>();
        try {
            SQLiteDatabase db = mDbHelper.getReadableDatabase();
            Cursor c = db.query(AirportDBHelper.AirportEntry.TABLE_NAME, AirportDBHelper.selectAllAirports,
                    null, null, null, null, null);
            if (c != null && c.getCount() > 0) {
                while (c.moveToNext()) {
                    String name = c.getString(c.getColumnIndexOrThrow(AirportDBHelper.AirportEntry.COLUMN_NAME_NAME));
                    String code = c.getString(c.getColumnIndexOrThrow(AirportDBHelper.AirportEntry.COLUMN_NAME_CODE));
                    String country = c.getString(c.getColumnIndexOrThrow(AirportDBHelper.AirportEntry.COLUMN_NAME_COUNTRY));
                    String latitude = c.getString(c.getColumnIndexOrThrow(AirportDBHelper.AirportEntry.COLUMN_NAME_LATITUDE));
                    String longitude = c.getString(c.getColumnIndexOrThrow(AirportDBHelper.AirportEntry.COLUMN_NAME_LONGITUDE));
                    Airport Airport = new Airport(name, code, country, latitude, longitude);
                    miasta.add(Airport);
                }
            }
            if (c != null) {
                c.close();
            }
        } catch (IllegalStateException e) {
            // Send to analytics, log etc
        }
        return miasta;
    }

    public void saveAirport(Airport airport) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AirportDBHelper.AirportEntry.COLUMN_NAME_ID, airport.getCode());
        values.put(AirportDBHelper.AirportEntry.COLUMN_NAME_NAME, airport.getName());
        values.put(AirportDBHelper.AirportEntry.COLUMN_NAME_CODE, airport.getCode());
        values.put(AirportDBHelper.AirportEntry.COLUMN_NAME_COUNTRY, airport.getCountry());
        values.put(AirportDBHelper.AirportEntry.COLUMN_NAME_LATITUDE, airport.getLatitude());
        values.put(AirportDBHelper.AirportEntry.COLUMN_NAME_LONGITUDE, airport.getLongitude());
        db.insert(AirportDBHelper.AirportEntry.TABLE_NAME, null, values);
        db.close();
    }
}
