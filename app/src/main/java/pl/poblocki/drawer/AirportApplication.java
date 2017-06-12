package pl.poblocki.drawer;

import android.app.Application;

import pl.poblocki.drawer.di.AppComponent;

/**
 * Created by Iza on 10.06.2017.
 */

public class AirportApplication extends Application {
    private static AirportApplication instance;
    private static AppComponent graph;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        buildComponentGraph();
    }

    public static AppComponent component() {
        return graph;
    }

    public static void buildComponentGraph() {
        graph = AppComponent.Initializer.init(instance);
    }
}
