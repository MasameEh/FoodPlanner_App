package com.example.foodplanner.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMealsPicker {
    static List<String> mealAreas = new ArrayList<>();
    static {

        mealAreas.add("American");
        mealAreas.add("British");
        mealAreas.add("Canadian");
        mealAreas.add("Chinese");
        mealAreas.add("Croatian");
        mealAreas.add("Dutch");
        mealAreas.add("Egyptian");
        mealAreas.add("Filipino");
        mealAreas.add("French");
        mealAreas.add("Greek");
        mealAreas.add("Indian");
        mealAreas.add("Irish");
        mealAreas.add("Italian");
        mealAreas.add("Jamaican");
        mealAreas.add("Japanese");
        mealAreas.add("Kenyan");
        mealAreas.add("Malaysian");
        mealAreas.add("Mexican");
        mealAreas.add("Moroccan");
        mealAreas.add("Polish");
        mealAreas.add("Portuguese");
        mealAreas.add("Russian");
        mealAreas.add("Spanish");
        mealAreas.add("Thai");
        mealAreas.add("Tunisian");
        mealAreas.add("Turkish");
        mealAreas.add("Ukrainian");
        mealAreas.add("Uruguayan");
        mealAreas.add("Vietnamese");
    }

    public static String getRandomMealArea() {
        Random random = new Random();
        return mealAreas.get(random.nextInt(mealAreas.size()));
    }
}
