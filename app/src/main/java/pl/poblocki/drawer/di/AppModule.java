package pl.poblocki.drawer.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.poblocki.drawer.AirportApplication;

@Module
public class AppModule {
//    private AirportApplication app;
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }
//    public AppModule(AirportApplication application) {
//        app = application;
//    }
//
//    @Provides
//    @Singleton
//    protected Application provideApplication() {
//        return app;
//    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }
}
