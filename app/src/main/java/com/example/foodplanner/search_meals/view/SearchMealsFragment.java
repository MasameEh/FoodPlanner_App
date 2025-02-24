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
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.local.db.MealFavs.MealLocalDataSourceImp;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSourceImp;
import com.example.foodplanner.data.remote.network.Meal.MealRemoteDataSourceImp;
import com.example.foodplanner.data.repo.FirebaseRepositoryImp;
import com.example.foodplanner.data.repo.meal_repo.MealRepositoryImp;
import com.example.foodplanner.search_meals.presenter.SearchMealPresenterImp;
import com.example.foodplanner.search_meals.presenter.SearchMealsPresenter;
import com.example.foodplanner.utils.CustomToast;

import java.util.List;


public class SearchMealsFragment extends Fragment implements SearchMealView, OnMealClickListener {

    private static final String TAG = "SearchMealsFragment";

    private SearchMealsPresenter presenter;
    private String type, name;
    private RecyclerView rv;

    private EditText searchEt;
    private TextView selectedName;

    private MealRecyclerViewAdapter mealAdapter;

    public SearchMealsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new SearchMealPresenterImp(
                MealRepositoryImp.getInstance(
                        FirebaseRepositoryImp.getInstance(FirebaseRemoteDataSourceImp.getInstance(),
                                CacheHelper.getInstance(requireContext())),
                        MealRemoteDataSourceImp.getInstance(),
                        MealLocalDataSourceImp.getInstance(requireContext()))
                ,this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         name = SearchMealsFragmentArgs.fromBundle(getArguments()).getName();
         type = SearchMealsFragmentArgs.fromBundle(getArguments()).getType();



        Log.i(TAG, "onCreateView: name: " + name + " " + type  );
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeUI(view);

        presenter.getMealsData(type, name);

        selectedName.setText(name);

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


    void initializeUI(View view){
        rv = view.findViewById(R.id.rv_meals);
        searchEt = view.findViewById(R.id.et_meals_search);
        selectedName = view.findViewById(R.id.tv_name);
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
        CustomToast.showCustomErrToast(requireContext(), message);
    }

    @Override
    public void onMealClicked(String mealId) {
        SearchMealsFragmentDirections.ActionSearchMealsFragmentToMealDetailsFragment action =
                SearchMealsFragmentDirections.actionSearchMealsFragmentToMealDetailsFragment(mealId);
        NavHostFragment.findNavController(this).navigate(action);
    }
}