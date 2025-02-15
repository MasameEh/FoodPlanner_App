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


import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewHolder>{
    private static final String TAG = "CategoryRecyclerViewAdapter";
    private final Context context;
    private final List<Category> dataSet;
    public CategoryRecyclerViewAdapter(Context context, List<Category> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    //if view (row) is null
    public CategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        //inflation (parsing) xml to view objects
        LayoutInflater inflater =  LayoutInflater.from(context);
        // parent : viewGroup to put view on
        View view =  inflater.inflate(R.layout.single_item, parent, false);

        return new CategoryRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolder holder, int position) {
        Category currMeal = dataSet.get(position);

        holder.itemName.setText(currMeal.getCategoryName());
        Glide.with(context).load(currMeal.getCategoryImage()).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    public void setData(List<Category> categories) {
        dataSet.clear();
        dataSet.addAll(categories);
        notifyDataSetChanged();
    }
}
class CategoryRecyclerViewHolder extends RecyclerView.ViewHolder{

    View conView;
    ImageView itemImage;
    TextView itemName;

    public CategoryRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        conView = itemView;
        itemName = itemView.findViewById(R.id.tv_item_name);
        itemImage = itemView.findViewById(R.id.iv_item_thumbnail);
    }
}