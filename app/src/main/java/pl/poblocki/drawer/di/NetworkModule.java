package pl.poblocki.drawer.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.poblocki.drawer.manager.FlightManager;

@Module
public class NetworkModule {
    private FlightManager manager;

    public NetworkModule(FlightManager manager) {
        this.manager = manager;
    }

    @Provides
    @Singleton
    public FlightManager provideManager() {
        return manager;
    }
}
