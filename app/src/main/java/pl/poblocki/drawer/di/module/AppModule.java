package pl.poblocki.drawer.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import pl.poblocki.drawer.di.scope.ApplicationScope;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    Context provideContext() {
        return context;
    }
}
