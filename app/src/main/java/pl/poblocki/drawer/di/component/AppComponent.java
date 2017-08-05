package pl.poblocki.drawer.di.component;

import javax.inject.Singleton;

import dagger.Component;
import pl.poblocki.drawer.AirportApplication;
import pl.poblocki.drawer.di.module.AppModule;
import pl.poblocki.drawer.AirportActivity;
import pl.poblocki.drawer.view.ButtonFragment;
import pl.poblocki.drawer.flights.ItemPagerFragment;

//@ApplicationScope
@Singleton
@Component(modules = {AppModule.class
//        , NetworkModule.class
})
public interface AppComponent {

//    ActivityComponent plus(ActivityModule activityModule);
    void inject(AirportActivity airportActivity);
    void inject(ButtonFragment buttonFragment);
    void inject(ItemPagerFragment itemPagerFragment);

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
