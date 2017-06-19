package pl.poblocki.drawer.di.component;

import dagger.Component;
import pl.poblocki.drawer.AirportApplication;
import pl.poblocki.drawer.di.module.ActivityModule;
import pl.poblocki.drawer.di.module.AppModule;
import pl.poblocki.drawer.di.module.NetworkModule;
import pl.poblocki.drawer.di.scope.ApplicationScope;

@ApplicationScope
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    ActivityComponent plus(ActivityModule activityModule);

    final class Initializer {
        private Initializer() {
        }

        public static AppComponent init(AirportApplication app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }
    }
}
