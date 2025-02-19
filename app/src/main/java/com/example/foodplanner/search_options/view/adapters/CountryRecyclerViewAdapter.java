package com.example.foodplanner.search_options.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.foodplanner.R;
import com.example.foodplanner.data.model.Meal;
import com.example.foodplanner.search_options.view.OnItemClickListener;
import com.example.foodplanner.utils.CountryMapper;

public class CountryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private static final String TAG = "SearchRecyclerViewAdapter";
    private final Context context;
    private final List<Meal> dataSet;
    private final OnItemClickListener listener;
    public CountryRecyclerViewAdapter(Context context, List<Meal> dataSet, OnItemClickListener listener) {
        this.context = context;
        this.dataSet = dataSet;
        this.listener = listener;
    }
    @NonNull
    @Override
    //if view (row) is null
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflation (parsing) xml to view objects
        LayoutInflater inflater =  LayoutInflater.from(context);
        // parent : viewGroup to put view on
        View view =  inflater.inflate(R.layout.general_item, parent, false);

        return new RecyclerViewHolder(view);
    }

    // populate items
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Meal currCountry = dataSet.get(position);

        holder.itemName.setText(currCountry.getMealCountry());
        holder.itemImage.setImageResource(CountryMapper.getImageForCountry(currCountry.getMealCountry()));
        holder.itemCard.setOnClickListener(
                v -> {
                    listener.onItemClick(currCountry.getMealCountry(), "country");
                }
        );

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
    ImageView itemImage;
    TextView itemName;

    CardView itemCard;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        conView = itemView;
        itemName = itemView.findViewById(R.id.tv_general_item_name);
        itemImage = itemView.findViewById(R.id.iv_general_item_image);
        itemCard = itemView.findViewById(R.id.cv_general);
    }
}