package com.example.foodplanner.search_meals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.foodplanner.data.model.Meal;


public class SearchFragment extends Fragment {


    ChipGroup chipGroup;
    RecyclerView recyclerView;
    List<Meal> mealsCountry;
    List<Meal> mealsInger;
    List<Meal> mealsCatg;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requireActivity().setTitle("SearchFragment");

        mealsInger = new ArrayList<>(Arrays.asList(
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg"),
                new Meal("Apple & Blackberry Crumble", "https://www.themealdb.com/images/media/meals/xvsurr1511719182.jpg"),
                new Meal("Ayam Percik", "https://www.themealdb.com/images/media/meals/020z181619788503.jpg"),
                new Meal("Fish pie", "https://www.themealdb.com/images/media/meals/ysxwuq1487323065.jpg"),
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/sqrtwu1511721265.jpg"),
                new Meal("Flamiche", "https://www.themealdb.com/images/media/meals/wssvvs1511785879.jpg"),
                new Meal("French Onion Soup", "https://www.themealdb.com/images/media/meals/xvrrux1511783685.jpg"),
                new Meal("Full English Breakfast", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg"),
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg"),
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg"),
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg")
        ));

        mealsCountry = new ArrayList<>(Arrays.asList(
                new Meal("American", "https://cdn.britannica.com/33/4833-050-F6E415FE/Flag-United-States-of-America.jpg"),
                new Meal("British", "https://cdn.britannica.com/33/4833-050-F6E415FE/Flag-United-States-of-America.jpg"),
                new Meal("American", "https://www.themealdb.com/images/media/meals/020z181619788503.jpg"),
                new Meal("American", "https://cdn.britannica.com/33/4833-050-F6E415FE/Flag-United-States-of-America.jpg"),
                new Meal("British", "https://cdn.britannica.com/33/4833-050-F6E415FE/Flag-United-States-of-America.jpg"),
                new Meal("British", "https://www.themealdb.com/images/media/meals/wssvvs1511785879.jpg"),
                new Meal("French Onion Soup", "https://www.themealdb.com/images/media/meals/xvrrux1511783685.jpg"),
                new Meal("Full English Breakfast", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg"),
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg"),
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg"),
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg")
        ));

        mealsCatg = new ArrayList<>(Arrays.asList(
                new Meal("Beef", "https://www.themealdb.com/images/category/beef.png"),
                new Meal("Chicken", "https://www.themealdb.com/images/category/chicken.png"),
                new Meal("Dessert", "https://www.themealdb.com/images/category/dessert.png"),
                new Meal("Lamb", "https://www.themealdb.com/images/category/lamb.png"),
                new Meal("British", "https://cdn.britannica.com/33/4833-050-F6E415FE/Flag-United-States-of-America.jpg"),
                new Meal("British", "https://www.themealdb.com/images/category/chicken.png"),
                new Meal("French Onion Soup", "https://www.themealdb.com/images/category/chicken.png"),
                new Meal("Full English Breakfast", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg"),
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg"),
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg"),
                new Meal("Apple Frangipan Tart", "https://www.themealdb.com/images/media/meals/wxywrq1468235067.jpg")
        ));

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
        chipGroup = view.findViewById(R.id.chipGroup);
        recyclerView = view.findViewById(R.id.rv_items);
        int spacing = 16; // 16dp spacing
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, true));
        setupChoiceChips();


    }

    private void setupChoiceChips(){

        for(int i = 0; i < chipGroup.getChildCount(); i++){
            Chip chip = (Chip)chipGroup.getChildAt(i);

            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if(isChecked && chip.getText().equals("Countries")){
                    Toast.makeText(requireContext(), chip.getText(), Toast.LENGTH_SHORT).show();
                    SearchRecyclerViewAdapter myRv = new SearchRecyclerViewAdapter(requireContext(), mealsCountry);
                    recyclerView.setAdapter(myRv);
                }
                else if(isChecked && chip.getText().equals("Ingredients")){
                    Toast.makeText(requireContext(), chip.getText(), Toast.LENGTH_SHORT).show();
                    SearchRecyclerViewAdapter myRv = new SearchRecyclerViewAdapter(requireContext(), mealsInger);
                    recyclerView.setAdapter(myRv);
                }
                else if(isChecked && chip.getText().equals("Categories")){
                    Toast.makeText(requireContext(), chip.getText(), Toast.LENGTH_SHORT).show();
                    SearchRecyclerViewAdapter myRv = new SearchRecyclerViewAdapter(requireContext(), mealsCatg);
                    recyclerView.setAdapter(myRv);
                }
                else{
                    SearchRecyclerViewAdapter myRv = new SearchRecyclerViewAdapter(requireContext(), new ArrayList<>());
                    recyclerView.setAdapter(myRv);
                }
            });

        }
    }
}