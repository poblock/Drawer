package pl.poblocki.drawer.model;

/**
 * Created by Iza on 16.06.2017.
 */

public class DBTime {
    private long id;
    private String dbName;
    private String dbTime;

    public DBTime() {
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
