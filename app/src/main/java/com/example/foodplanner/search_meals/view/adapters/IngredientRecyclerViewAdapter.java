package com.example.foodplanner.search_meals.view.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.data.model.Ingredient;
import com.example.foodplanner.data.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class IngredientRecyclerViewAdapter extends RecyclerView.Adapter<IngredientRecyclerViewHolder>{


    private static final String TAG = "CategoryRecyclerViewAdapter";
    private static String imageUrl = "https://www.themealdb.com/images/ingredients/";
    private final Context context;
    private final List<Ingredient> dataSet;
    public IngredientRecyclerViewAdapter(Context context, List<Ingredient> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public IngredientRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        //inflation (parsing) xml to view objects
        LayoutInflater inflater =  LayoutInflater.from(context);
        // parent : viewGroup to put view on
        View view =  inflater.inflate(R.layout.single_item, parent, false);

        return new IngredientRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientRecyclerViewHolder holder, int position) {
        Ingredient currIngredient = dataSet.get(position);
        String ingredientImage = imageUrl + currIngredient.getName() + ".png";
        holder.itemName.setText(currIngredient.getName());
        Glide.with(context).load(ingredientImage).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public void setData(List<Ingredient> ingredients) {
        dataSet.clear();
        dataSet.addAll(ingredients);
        notifyDataSetChanged();
    }
}

class IngredientRecyclerViewHolder extends RecyclerView.ViewHolder{

    View conView;
    ImageView itemImage;
    TextView itemName;


    public IngredientRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        conView = itemView;
        itemName = itemView.findViewById(R .id.tv_item_name);
        itemImage = itemView.findViewById(R.id.iv_item_thumbnail);
    }
}