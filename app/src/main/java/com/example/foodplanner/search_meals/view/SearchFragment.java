package com.example.foodplanner.search_meals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.remote.network.Category.CategoriesRemoteDataSourceImp;
import com.example.foodplanner.data.remote.network.Meal.MealsRemoteDataSourceImp;
import com.example.foodplanner.data.repo.CategoryRepositoryImp;
import com.example.foodplanner.data.repo.MealsRepositoryImp;
import com.example.foodplanner.search_meals.presenter.SearchPresenter;
import com.example.foodplanner.search_meals.presenter.SearchPresenterImp;
import com.example.foodplanner.search_meals.view.adapters.CategoryRecyclerViewAdapter;
import com.example.foodplanner.search_meals.view.adapters.IngredientRecyclerViewAdapter;
import com.example.foodplanner.search_meals.view.adapters.CountryRecyclerViewAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import com.example.foodplanner.data.model.Meal;


public class SearchFragment extends Fragment implements SearchView{


    ChipGroup chipGroup;
    RecyclerView recyclerView;
    EditText searchEt;
    String selectedChip;

    IngredientRecyclerViewAdapter ingredientAdapter;
    CategoryRecyclerViewAdapter categoryAdapter;

    CountryRecyclerViewAdapter countryAdapter;
    private static final String TAG = "SearchFragment";
    SearchPresenter searchPresenter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().setTitle("SearchFragment");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchPresenter = new SearchPresenterImp(
                CategoryRepositoryImp.getInstance(CategoriesRemoteDataSourceImp.getInstance()),
                MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance())
                ,this);

        initializeUI(view);
        setupChoiceChips();

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (selectedChip.equals("Categories")) {

                    searchPresenter.filterCategories(s.toString());
                    Log.i(TAG, "onTextChanged: " + s);
                } else if (selectedChip.equals("Ingredients")) {
                    searchPresenter.filterIngredients(s.toString());
                } else if (selectedChip.equals("Countries")) {
                    Log.i(TAG, "onTextChanged: " + s);
                    searchPresenter.filterCountries(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        int spacing = 16;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, true));

    }

    private void setupChoiceChips(){

        for(int i = 0; i < chipGroup.getChildCount(); i++){
            Chip chip = (Chip)chipGroup.getChildAt(i);

            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked && chip.getText().equals("Countries")){
                    Toast.makeText(requireContext(), chip.getText(), Toast.LENGTH_SHORT).show();
                    searchPresenter.getCountries();
                    selectedChip = "Countries";

                }
                else if(isChecked && chip.getText().equals("Ingredients")){
                    Toast.makeText(requireContext(), chip.getText(), Toast.LENGTH_SHORT).show();
                    searchPresenter.getIngredients();
                    selectedChip = "Ingredients";
                }
                else if(isChecked && chip.getText().equals("Categories")){
                    Toast.makeText(requireContext(), chip.getText(), Toast.LENGTH_SHORT).show();
                    searchPresenter.getCategories();
                    selectedChip = "Categories";
                }
            });

        }
    }

    public void initializeUI(View view){
        chipGroup = view.findViewById(R.id.chipGroup);
        recyclerView = view.findViewById(R.id.rv_items);
        searchEt =  view.findViewById(R.id.et_search);
    }


    @Override
    public void showCategories(List<Category> categories) {
        Log.i(TAG, "showCategories: " + categories.get(0).getCategoryName());
         categoryAdapter = new CategoryRecyclerViewAdapter(requireContext(), categories);
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void showCountries(List<Meal> countries) {
        countryAdapter = new CountryRecyclerViewAdapter(requireContext(), countries);
        Log.i(TAG, "showCountries: " + countries.get(0).getMealCountry());
        recyclerView.setAdapter(countryAdapter);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        Log.i(TAG, "showIngredients: " + ingredients.get(0).getName());
        ingredientAdapter =  new IngredientRecyclerViewAdapter(requireContext(), ingredients);
        recyclerView.setAdapter(ingredientAdapter);
    }

    @Override
    public void showFilteredCountries(List<Meal> countries) {
        Log.i(TAG, "countryAdapter: " + countries);
        countryAdapter.setData(countries);
    }

    @Override
    public void showFilteredCategories(List<Category> categories) {
        Log.i(TAG, "showFilteredCategories: " + categories);
        categoryAdapter.setData(categories);
    }

    @Override
    public void showFilteredIngredients(List<Ingredient> ingredients) {
        ingredientAdapter.setData(ingredients);
    }

    @Override
    public void showErr(String err) {

    }
}