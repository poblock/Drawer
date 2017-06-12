package pl.poblocki.drawer.di;


import javax.inject.Singleton;

import dagger.Component;
import pl.poblocki.drawer.AirportApplication;
import pl.poblocki.drawer.view.AirportActivity;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(AirportActivity mainActivity);

    static final class Initializer {
        private Initializer() {
        }

        public static AppComponent init(AirportApplication app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }
    }
}
