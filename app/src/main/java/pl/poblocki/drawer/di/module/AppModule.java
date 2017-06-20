package pl.poblocki.drawer.di.module;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import pl.poblocki.drawer.data.AirportRepository;
import pl.poblocki.drawer.data.db.AirportLocalSource;
import pl.poblocki.drawer.manager.FlightManager;
import pl.poblocki.drawer.data.network.API;
import pl.poblocki.drawer.support.ResourceSupport;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    ResourceSupport provideResourceSupport(Context context) {
        return new ResourceSupport(context);
    }

    @Provides
    @Singleton
    AirportLocalSource provideLocalSource(Context context) {return new AirportLocalSource(context);}

    @Provides
    @Named("URL")
    String provideBaseUrlString() {
        return "https://agile-dawn-87098.herokuapp.com";
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(5, TimeUnit.MINUTES)
                .build();
        return client;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, OkHttpClient client, @Named("URL") String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    API provideAPI(Retrofit retrofit) {
        return retrofit.create(API.class);
    }

    @Provides
    @Singleton
    FlightManager provideManager(API serverAPI) {
        return new FlightManager(serverAPI);
    }

    @Provides
    @Singleton
    AirportRepository provideDataRepository(API remoteSource, AirportLocalSource localSource) {
        return new AirportRepository(remoteSource, localSource);
    }
}
