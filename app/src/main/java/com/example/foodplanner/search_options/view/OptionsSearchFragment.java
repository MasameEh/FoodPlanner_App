package com.example.foodplanner.search_options.view;

import static android.view.View.GONE;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.data.local.CacheHelper;
import com.example.foodplanner.data.local.db.MealFavs.MealLocalDataSourceImp;
import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.remote.auth.FirebaseRemoteDataSourceImp;
import com.example.foodplanner.data.remote.network.Category.CategoriesRemoteDataSourceImp;
import com.example.foodplanner.data.remote.network.Meal.MealRemoteDataSourceImp;
import com.example.foodplanner.data.repo.FirebaseRepositoryImp;
import com.example.foodplanner.data.repo.category_repo.CategoryRepositoryImp;
import com.example.foodplanner.data.repo.meal_repo.MealRepositoryImp;
import com.example.foodplanner.search_options.presenter.OptionsSearchPresenter;
import com.example.foodplanner.search_options.presenter.OptionsSearchPresenterImp;
import com.example.foodplanner.search_options.view.adapters.CategoryRecyclerViewAdapter;
import com.example.foodplanner.search_options.view.adapters.IngredientRecyclerViewAdapter;
import com.example.foodplanner.search_options.view.adapters.CountryRecyclerViewAdapter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

import com.example.foodplanner.data.model.Meal;


public class OptionsSearchFragment extends Fragment implements OptionsSearchView, OnItemClickListener  {


    private ChipGroup chipGroup;
    private RecyclerView recyclerView;
    private EditText searchEt;
    private String selectedChip;
    private TextView searchOptionsTv;
    private ImageView searchIv;
    private IngredientRecyclerViewAdapter ingredientAdapter;
    private CategoryRecyclerViewAdapter categoryAdapter;

    private CountryRecyclerViewAdapter countryAdapter;
    private static final String TAG = "SearchFragment";
    private OptionsSearchPresenter optionsSearchPresenter;

    public OptionsSearchFragment() {
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
        return inflater.inflate(R.layout.fragment_options_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        optionsSearchPresenter = new OptionsSearchPresenterImp(
                CategoryRepositoryImp.getInstance(CategoriesRemoteDataSourceImp.getInstance()),
                MealRepositoryImp.getInstance(FirebaseRepositoryImp.getInstance(FirebaseRemoteDataSourceImp.getInstance(),
                                CacheHelper.getInstance(requireContext())),
                        MealRemoteDataSourceImp.getInstance(),
                        MealLocalDataSourceImp.getInstance(requireContext()))
                        ,this);

        initializeUI(view);
        setupChoiceChips();
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        recyclerView.addItemDecoration(new ItemSpacingDecoration(2, spacingInPixels, true));

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (selectedChip.equals("Categories")) {

                    optionsSearchPresenter.filterCategories(s.toString());
                    Log.i(TAG, "onTextChanged: " + s);
                } else if (selectedChip.equals("Ingredients")) {
                    optionsSearchPresenter.filterIngredients(s.toString());
                } else if (selectedChip.equals("Countries")) {
                    Log.i(TAG, "onTextChanged: " + s);
                    optionsSearchPresenter.filterCountries(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setupChoiceChips(){
        for(int i = 0; i < chipGroup.getChildCount(); i++){
            Chip chip = (Chip)chipGroup.getChildAt(i);

            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked && chip.getText().equals("Countries")){
                    Toast.makeText(requireContext(), chip.getText(), Toast.LENGTH_SHORT).show();
                    optionsSearchPresenter.getCountries();
                    selectedChip = "Countries";

                }
                else if(isChecked && chip.getText().equals("Ingredients")){
                    Toast.makeText(requireContext(), chip.getText(), Toast.LENGTH_SHORT).show();
                    optionsSearchPresenter.getIngredients();
                    selectedChip = "Ingredients";
                }
                else if(isChecked && chip.getText().equals("Categories")){
                    Toast.makeText(requireContext(), chip.getText(), Toast.LENGTH_SHORT).show();
                    optionsSearchPresenter.getCategories();
                    selectedChip = "Categories";
                }
            });

        }
    }

    public void initializeUI(View view){
        chipGroup = view.findViewById(R.id.chipGroup);
        recyclerView = view.findViewById(R.id.rv_items);
        searchEt =  view.findViewById(R.id.et_filter);
        searchOptionsTv = view.findViewById(R.id.tv_search_options);
        searchIv = view.findViewById(R.id.iv_search);
    }


    @Override
    public void showCategories(List<Category> categories) {
        Log.i(TAG, "showCategories: " + categories.get(0).getCategoryName());
        searchOptionsTv.setVisibility(GONE);
        searchIv.setVisibility(GONE);
                categoryAdapter = new CategoryRecyclerViewAdapter(requireContext(), categories, this);
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void showCountries(List<Meal> countries) {
        searchOptionsTv.setVisibility(GONE);
        searchIv.setVisibility(GONE);
        countryAdapter = new CountryRecyclerViewAdapter(requireContext(), countries, this);
        Log.i(TAG, "showCountries: " + countries.get(0).getMealCountry());
        recyclerView.setAdapter(countryAdapter);
    }

    @Override
    public void showIngredients(List<Ingredient> ingredients) {
        searchOptionsTv.setVisibility(GONE);
        searchIv.setVisibility(GONE);
        Log.i(TAG, "showIngredients: " + ingredients.get(0).getName());
        ingredientAdapter =  new IngredientRecyclerViewAdapter(requireContext(), ingredients, this);
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

    @Override
    public void onItemClick(String name, String type) {
        selectedChip = "";
        OptionsSearchFragmentDirections.ActionSearchFragmentToSearchMealsFragment action =
                OptionsSearchFragmentDirections.actionSearchFragmentToSearchMealsFragment(name, type);
        NavHostFragment.findNavController(this).navigate(action);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        optionsSearchPresenter.clear();
    }
}