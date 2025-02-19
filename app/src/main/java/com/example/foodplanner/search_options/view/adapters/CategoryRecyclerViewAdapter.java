package com.example.foodplanner.search_options.view.adapters;

import android.content.Context;
import android.util.Log;
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
import com.example.foodplanner.data.model.Category;
import com.example.foodplanner.search_options.view.OnItemClickListener;


import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewHolder>{
    private static final String TAG = "CategoryRecyclerViewAdapter";
    private final Context context;
    private final List<Category> dataSet;
    private final OnItemClickListener listener;
    public CategoryRecyclerViewAdapter(Context context, List<Category> dataSet, OnItemClickListener listener) {
        this.context = context;
        this.dataSet = dataSet;
        this.listener = listener;
    }

    @NonNull
    @Override
    //if view (row) is null
    public CategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        //inflation (parsing) xml to view objects
        LayoutInflater inflater =  LayoutInflater.from(context);
        // parent : viewGroup to put view on
        View view =  inflater.inflate(R.layout.general_item, parent, false);

        return new CategoryRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolder holder, int position) {
        Category currMeal = dataSet.get(position);

        holder.itemName.setText(currMeal.getCategoryName());
        holder.itemCard.setOnClickListener(
                v -> {
                    listener.onItemClick(currMeal.getCategoryName(), "category");
                }
        );
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
    CardView itemCard;

    public CategoryRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        conView = itemView;
        itemName = itemView.findViewById(R.id.tv_general_item_name);
        itemImage = itemView.findViewById(R.id.iv_general_item_image);
        itemCard = itemView.findViewById(R.id.cv_general);
    }
}