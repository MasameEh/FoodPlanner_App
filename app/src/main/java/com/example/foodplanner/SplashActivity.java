package com.example.foodplanner;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

import model.Category;
import model.CategoryResponse;
import network.MealService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {

    public static final String baseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static final String TAG = "SplashActivity";
    private static final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        LottieAnimationView lottieAnimationView = findViewById(R.id.lottie_splash);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MealService mealService = retrofit.create(MealService.class);

        Call<CategoryResponse> call = mealService.getCategories();

        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    CategoryResponse products = response.body();
                    List<Category> productsList = products.getCategories();
                    Log.i(TAG, "onResponse: " + response.body().getCategories().size());
                    int sizeOfList = productsList.size();
                    Toast.makeText(SplashActivity.this, "Number of products: " + sizeOfList,Toast.LENGTH_SHORT).show();
                    
                } else {
                    // Handle errors (e.g., HTTP 404, 500)
                    Toast.makeText(SplashActivity.this, "Number of products: " + response.errorBody(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                // Handle network failure or request failure
            }
        });
    }
}