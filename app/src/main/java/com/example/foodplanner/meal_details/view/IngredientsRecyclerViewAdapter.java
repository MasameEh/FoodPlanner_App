package com.example.foodplanner.meal_details.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Ingredient;

import java.util.List;

public class IngredientsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private final Context context;
    private static String imageUrl = "https://www.themealdb.com/images/ingredients/";
    private final List<Ingredient> dataSet;

    public IngredientsRecyclerViewAdapter(Context context, List<Ingredient> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        // parent : viewGroup to put view on
        View view =  inflater.inflate(R.layout.ingredient_card, parent, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Ingredient currIngredient = dataSet.get(position);
        String ingredientImage = imageUrl + currIngredient.getName() + "-Small.png";
        holder.ingredientName.setText(currIngredient.getName());
        holder.ingredientMeasure.setText(currIngredient.getMeasure());
        Glide.with(context).load(ingredientImage).into(holder.ingredientImage);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
class RecyclerViewHolder extends RecyclerView.ViewHolder{

    View conView;
    ImageView ingredientImage;
    TextView ingredientName;
    TextView ingredientMeasure;


    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        conView = itemView;
        ingredientName = itemView.findViewById(R.id.tv_ingredient);
        ingredientImage = itemView.findViewById(R.id.iv_ingredient);
        ingredientMeasure = itemView.findViewById(R.id.tv_measure);
    }
}