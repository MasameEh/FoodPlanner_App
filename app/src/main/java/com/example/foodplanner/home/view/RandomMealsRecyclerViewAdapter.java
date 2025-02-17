package com.example.foodplanner.home.view;

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
import com.example.foodplanner.data.model.Meal;

import java.util.List;

public class RandomMealsRecyclerViewAdapter extends RecyclerView.Adapter<RandomRecyclerViewHolder>{
    private static final String TAG = "CategoryRecyclerViewAdapter";
    private final Context context;
    private final List<Meal> dataSet;
    public RandomMealsRecyclerViewAdapter(Context context, List<Meal> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    //if view (row) is null
    public RandomRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        //inflation (parsing) xml to view objects
        LayoutInflater inflater =  LayoutInflater.from(context);
        // parent : viewGroup to put view on
        View view =  inflater.inflate(R.layout.single_item, parent, false);

        return new RandomRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecyclerViewHolder holder, int position) {
        Meal currMeal = dataSet.get(position);


        holder.itemName.setText(currMeal.getMealName());
        Glide.with(context).load(currMeal.getMealImage()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public void setData(List<Meal> meals) {
        dataSet.clear();
        dataSet.addAll(meals);
        notifyDataSetChanged();
    }
}
class RandomRecyclerViewHolder extends RecyclerView.ViewHolder{

    View conView;
    ImageView itemImage;
    TextView itemName;

    public RandomRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        conView = itemView;
        itemName = itemView.findViewById(R.id.tv_item_name);
        itemImage = itemView.findViewById(R.id.iv_item_thumbnail);
    }
}