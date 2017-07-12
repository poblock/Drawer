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
import pl.poblocki.drawer.data.network.AirportRemoteAPI;
import pl.poblocki.drawer.data.network.AirportRemoteSource;
import pl.poblocki.drawer.data.FlightManager;
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
    @Singleton
    FlightManager provideManager() {return new FlightManager();}

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
    AirportRemoteAPI provideAPI(Retrofit retrofit) {
        return retrofit.create(AirportRemoteAPI.class);
    }

    @Provides
    @Singleton
    AirportRemoteSource provideAirportRemoteSource(AirportRemoteAPI serverAPI) {
        return new AirportRemoteSource(serverAPI);
    }

    @Provides
    @Singleton
    AirportRepository provideDataRepository(AirportRemoteSource remoteSource, AirportLocalSource localSource) {
        return new AirportRepository(remoteSource, localSource);
    }
}
