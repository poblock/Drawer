package pl.poblocki.drawer;

import android.app.Application;

/**
 * Created by Iza on 10.06.2017.
 */

public class AirportApplication extends Application {
    private static AirportApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        buildComponentGraph();
    }

    public static void buildComponentGraph() {

    }
}
