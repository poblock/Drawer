package pl.poblocki.drawer;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import pl.poblocki.drawer.di.component.AppComponent;

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    public static AppComponent component() {
        return graph;
    }

    public static void buildComponentGraph() {
        graph = AppComponent.Initializer.init(instance);
    }
}
