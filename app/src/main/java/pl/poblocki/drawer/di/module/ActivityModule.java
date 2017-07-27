package pl.poblocki.drawer.di.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import pl.poblocki.drawer.di.scope.ActivityScope;
import pl.poblocki.drawer.di.scope.ForActivity;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ForActivity
    @ActivityScope
    public Context provideContext() {
        return activity;
    }

//    @Provides
//    public ListBinder<FlightViewModel> provideListBinder(FlightsDiffCallback flightsDiffCallback) {
//        return new ListBinder<>(flightsDiffCallback);
//    }
}
