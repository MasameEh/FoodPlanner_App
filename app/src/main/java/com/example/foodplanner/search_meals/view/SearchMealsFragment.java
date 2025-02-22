package com.example.foodplanner.search_meals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.db.MealFavs.MealLocalDataSourceImp;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.network.Meal.MealRemoteDataSourceImp;
import com.example.foodplanner.data.repo.fav_meal_repo.MealRepositoryImp;
import com.example.foodplanner.search_meals.presenter.SearchMealPresenterImp;
import com.example.foodplanner.search_meals.presenter.SearchMealsPresenter;
import com.example.foodplanner.utils.CustomToast;

import java.util.List;


public class SearchMealsFragment extends Fragment implements SearchMealView, OnMealClickListener {

    private static final String TAG = "SearchMealsFragment";

    private SearchMealsPresenter presenter;

    private  RecyclerView rv;

    private EditText searchEt;

    private MealRecyclerViewAdapter mealAdapter;

    public SearchMealsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new SearchMealPresenterImp(
                MealRepositoryImp.getInstance(
                        MealRemoteDataSourceImp.getInstance(),
                        MealLocalDataSourceImp.getInstance(requireContext()))
                ,this);
        String name = SearchMealsFragmentArgs.fromBundle(getArguments()).getName();
        String type = SearchMealsFragmentArgs.fromBundle(getArguments()).getType();

        switch(type){
            case "category":
                presenter.getMealsByCategory(name);
                break;
            case "country":
                presenter.getMealsByCountry(name);
                break;
            case "ingredient":
                presenter.getMealsByIngredient(name);
                break;
        }
        Log.i(TAG, "onCreateView: name: " + name + " " + type  );
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv = view.findViewById(R.id.rv_meals);
        searchEt = view.findViewById(R.id.et_meals_search);

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.filterMeals(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void showMealsData(List<Meal> meals) {
        Log.i(TAG, "showDate: " + meals.get(0).toString());
        mealAdapter = new MealRecyclerViewAdapter(requireContext(), meals, this);

        rv.setAdapter(mealAdapter);
    }

    @Override
    public void showFilteredMeals(List<Meal> meals) {
         mealAdapter.setData(meals);
    }

    @Override
    public void showError(String message) {
        CustomToast.showCustomToast(requireContext(), message);
    }

    @Override
    public void onMealClicked(String mealId) {
        SearchMealsFragmentDirections.ActionSearchMealsFragmentToMealDetailsFragment action =
                SearchMealsFragmentDirections.actionSearchMealsFragmentToMealDetailsFragment(mealId);
        NavHostFragment.findNavController(this).navigate(action);
    }
}