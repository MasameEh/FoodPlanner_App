package com.example.foodplanner.di;

import android.content.Context;

import com.example.foodplanner.data.local.db.MealFavs.FavoriteDatabase;
import com.example.foodplanner.data.local.db.MealFavs.MealDAO;
import com.example.foodplanner.data.remote.network.MealService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class DataProviderModule {
    private final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    @Provides
    @Singleton
    public Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // Convert JSON to Java objects
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
    @Provides
    public MealService provideMealService(Retrofit retrofit){
        return retrofit.create(MealService.class);
    }

    @Provides
    @Singleton
    public FavoriteDatabase provideDataBase(@ApplicationContext Context context){
        return FavoriteDatabase.getInstance(context);
    }

    @Provides
    public MealDAO provideDao (FavoriteDatabase db){
        return db.getMealDAO();
    }
}
