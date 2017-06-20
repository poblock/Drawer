package pl.poblocki.drawer.data.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import pl.poblocki.drawer.data.db.dao.AirlineDao;
import pl.poblocki.drawer.data.db.dao.AirportDao;
import pl.poblocki.drawer.data.db.dao.ConnectionDao;
import pl.poblocki.drawer.data.db.dao.DBTimeDao;
import pl.poblocki.drawer.model.Airline;
import pl.poblocki.drawer.model.Airport;
import pl.poblocki.drawer.model.Connection;
import pl.poblocki.drawer.model.DBTime;

public class AirportLocalSource {

//    private Context context;
    private SQLiteDatabase db;
//    private static AirportLocalSource INSTANCE = null;
    private AirportDBHelper mDbHelper;

    private AirlineDao airlineDao;
    private AirportDao airportDao;
    private ConnectionDao connectionDao;
    private DBTimeDao dbTimeDao;

    public AirportLocalSource(Context context) {
        mDbHelper = new AirportDBHelper(context);
        db = mDbHelper.getWritableDatabase();
        Log.i("DB", "Utworzono obiekt klasyDataManagerImpl created. Stan bazy: " + db.isOpen());


        airlineDao = new AirlineDao(db);
        airportDao = new AirportDao(db);
        connectionDao = new ConnectionDao(db);
        dbTimeDao = new DBTimeDao(db);
    }

//    public static AirportLocalSource getInstance(Context context) {
//        if(INSTANCE==null) {
//            INSTANCE = new AirportLocalSource(context);
//        }
//        return INSTANCE;
//    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public long saveAirline(Airline airline) {
        return airlineDao.save(airline);
    }

    public long saveAirport(Airport airport) {
        return airportDao.save(airport);
    }

    public long saveConnection(Connection connection) {
        long connectionID = 0L;

        // Operacje wykonujemy w ramach transakcji (z uwagi na stosowanie kilku tabel).
        try {
            db.beginTransaction();

//            // Najpierw zapisujemy film.
//            connectionID = movieDao.save(movie);
//
//            // Następnie sprawdzamy, czy kategoria istnieje, i zapisujemy powiązanie
//            // filmu z kategorią. Wymaga to kilku zapytań, jednak kategorii zwykle jest mało.
//            // Można też zapisać dane i przechwytywać wyjątki, jednak byłoby to nieeleganckie.
//            if (movie.getCategories().size() > 0) {
//                for (Category c : movie.getCategories()) {
//                    long catId = 0L;
//                    Category dbCat = categoryDao.find(c.getName());
//                    if (dbCat == null) {
//                        catId = categoryDao.save(c);
//                    } else {
//                        catId = dbCat.getId();
//                    }
//                    MovieCategoryKey mcKey = new MovieCategoryKey(connectionID, catId);
//                    if (!movieCategoryDao.exists(mcKey)) {
//                        movieCategoryDao.save(mcKey);
//                    }
//                }
//            }

            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("DB", "Błąd zapisu filmu (transakcję wycofano)", e);
            connectionID = 0L;
        } finally {
            // "Nazwa zastępcza" dla polecenia commit
            db.endTransaction();
        }

        return connectionID;
    }

    public long saveDBTime(DBTime dbTime) {
        return -1;
    }

    public void refreshAirports(List<Airport> airportList) {
        try {
            db.beginTransaction();
            airportDao.deleteAll();
            for(Airport airport : airportList) {
                airportDao.save(airport);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e("DB", "Błąd zapisu (transakcję wycofano)", e);
        } finally {
            db.endTransaction();
        }
    }

    public List<Airport> getAllAirports() {
        return airportDao.getAll();
    }

    public List<Airline> getAllAirlines() {
        return airlineDao.getAll();
    }

    public List<Connection> getAllConnections() {
        return null;
    }
}
