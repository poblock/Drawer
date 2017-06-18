package pl.poblocki.drawer.model;

/**
 * Created by Iza on 16.06.2017.
 */

public class DBTime {
    private String dbName;
    private String dbTime;

    public DBTime(String dbName, String dbTime) {
        this.dbName = dbName;
        this.dbTime = dbTime;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbTime() {
        return dbTime;
    }

    public void setDbTime(String dbTime) {
        this.dbTime = dbTime;
    }
}
