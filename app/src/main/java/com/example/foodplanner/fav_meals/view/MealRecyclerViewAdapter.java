package com.example.foodplanner.fav_meals.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.search_meals.view.OnMealClickListener;

import java.util.List;

public class MealRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private static final String TAG = "MealRecyclerViewAdapter";
    private final Context context;
    private final List<Meal> dataSet;

    private final OnRemoveIconClicked<Meal> listener;
    private final OnMealClickListener mealListener;
    public MealRecyclerViewAdapter(Context context, List<Meal> dataSet, OnRemoveIconClicked<Meal>  listener, OnMealClickListener mealListener) {
        this.context = context;
        this.dataSet = dataSet;
        this.listener = listener;
        this.mealListener = mealListener;
    }
    @NonNull
    @Override
    //if view (row) is null
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflation (parsing) xml to view objects
        LayoutInflater inflater =  LayoutInflater.from(context);
        // parent : viewGroup to put view on
        View view =  inflater.inflate(R.layout.fav_meal_item, parent, false);

        return new RecyclerViewHolder(view);
    }

    // populate items
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Meal currMeal = dataSet.get(position);

        holder.itemName.setText(currMeal.getMealName());
        Glide.with(context).load(currMeal.getMealImage()).into(holder.itemImage);

        holder.removeIcon.setOnClickListener(
                v -> {
                    listener.onIconClicked(currMeal);
                }
        );

        holder.itemCard.setOnClickListener(v -> {
            mealListener.onMealClicked(currMeal.getMealId());
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(List<Meal> countries) {
        dataSet.clear();
        dataSet.addAll(countries);
        notifyDataSetChanged();
    }

}

class RecyclerViewHolder extends RecyclerView.ViewHolder{

    View conView;
    ImageView itemImage, removeIcon;
    TextView itemName;

    CardView itemCard;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        conView = itemView;
        itemName = itemView.findViewById(R.id.tv_fav_meal);
        itemImage = itemView.findViewById(R.id.iv_fav_meal);
        removeIcon = itemView.findViewById(R.id.iv_remove_fav);
        itemCard = itemView.findViewById(R.id.cv_fav_meal);
    }
}