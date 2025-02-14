package com.example.foodplanner.search_meals.view;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Meal;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private static final String TAG = "MyRecyclerViewAdapter";
    private final Context context;
    private List<Meal> dataSet;
    Handler handler;

    public SearchRecyclerViewAdapter(Context context, List<Meal> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }
    @NonNull
    @Override
    //if view (row) is null
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        //inflation (parsing) xml to view objects
        LayoutInflater inflater =  LayoutInflater.from(context);
        // parent : viewGroup to put view on
        View view =  inflater.inflate(R.layout.single_item, parent, false);

        return new RecyclerViewHolder(view);
    }

    // populate items
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Meal currMeal = dataSet.get(position);

        holder.itemName.setText(currMeal.getMealName());
        Glide.with(context).load(currMeal.getMealImage()).into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
class RecyclerViewHolder extends RecyclerView.ViewHolder{

    View conView;
    ImageView itemImage;
    TextView itemName;


    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        conView = itemView;
        itemName = itemView.findViewById(R.id.tv_item_name);
        itemImage = itemView.findViewById(R.id.iv_item_thumbnail);
    }
}