package pl.poblocki.drawer.di.module;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import pl.poblocki.drawer.di.scope.ApplicationScope;
import pl.poblocki.drawer.manager.FlightManager;
import pl.poblocki.drawer.network.API;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@ApplicationScope
public class NetworkModule {

    @Provides
    @Named("URL")
    String provideBaseUrlString() {
        return "https://agile-dawn-87098.herokuapp.com";
    }

    @Provides
//    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
//    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
//    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, OkHttpClient client, @Named("URL") String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .client(client)
                .build();
    }

    @Provides
//    @Singleton
    API provideAPI(Retrofit retrofit) {
        return retrofit.create(API.class);
    }

    @Provides
//    @Singleton
    FlightManager provideManager(API serverAPI) {
        return new FlightManager(serverAPI);
    }
}
