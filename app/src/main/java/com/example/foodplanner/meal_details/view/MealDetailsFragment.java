package com.example.foodplanner.meal_details.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.Meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.data.repo.MealsRepositoryImp;
import com.example.foodplanner.meal_details.presenter.MealDetailsPresenter;
import com.example.foodplanner.meal_details.presenter.MealDetailsPresenterImp;
import com.example.foodplanner.utils.MealUtils;

import java.util.List;


public class MealDetailsFragment extends Fragment implements MealDetailsView{

    private static final String TAG = "MealDetailsFragment";

    MealDetailsPresenter presenter;
    private TextView mealName, mealCountry;
    private ImageView mealImage;

    private Meal meal;

    RecyclerView ingredientsRv;

    public MealDetailsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeUI(view);
        String mealId = MealDetailsFragmentArgs.fromBundle(getArguments()).getMealId();

        presenter = new MealDetailsPresenterImp(
                MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance()),
                this);

        presenter.getMealDetails(mealId);
    }


    public void initializeUI(View view){
        mealName = view.findViewById(R.id.tv_meal_name);
        mealCountry = view.findViewById(R.id.tv_meal_country);
        mealImage = view.findViewById(R.id.iv_meal_detail_image);
        ingredientsRv = view.findViewById(R.id.rv_meal_ingredients);
    }
    @Override
    public void showMealDetails(List<Meal> meals) {

        meal = meals.get(0);
        List<Ingredient> ingredientsList = MealUtils.getIngredientsList(meal);
        Log.i(TAG, "showMealDetails: " + ingredientsList + meal.getStrIngredient1());
        mealCountry.setText(meal.getMealCountry());
        mealName.setText(meal.getMealName());
        Glide.with(requireContext()).load(meal.getMealImage()).into(mealImage);

        IngredientsRecyclerViewAdapter adapter = new IngredientsRecyclerViewAdapter(requireContext(),ingredientsList);
        ingredientsRv.setAdapter(adapter);
    }

    @Override
    public void showError(String err) {

    }
}