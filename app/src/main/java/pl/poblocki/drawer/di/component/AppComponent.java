package pl.poblocki.drawer.di.component;

import android.support.v4.app.FragmentActivity;

import javax.inject.Singleton;

import dagger.Component;
import pl.poblocki.drawer.AirportApplication;
import pl.poblocki.drawer.di.module.ActivityModule;
import pl.poblocki.drawer.di.module.AppModule;
import pl.poblocki.drawer.di.module.NetworkModule;
import pl.poblocki.drawer.di.scope.ApplicationScope;
import pl.poblocki.drawer.view.AirportActivity;
import pl.poblocki.drawer.view.ButtonFragment;
import pl.poblocki.drawer.view.ItemPagerFragment;

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
