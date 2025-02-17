package com.example.foodplanner.data.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("idIngredient")
    private String id;

    @SerializedName("strIngredient")
    private String name;

    @SerializedName("strDescription")
    private String description;

    @SerializedName("strType")
    private String type;
    private String measure; // New field for measurement

    // Constructor for manually adding ingredients
    public Ingredient(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getMeasure() { return measure; }
    public String getDescription() { return description; }
    public String getType() { return type; }
}


