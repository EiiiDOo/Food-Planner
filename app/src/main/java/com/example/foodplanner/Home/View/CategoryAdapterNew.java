package com.example.foodplanner.Home.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.R;

import java.util.List;

public class CategoryAdapterNew extends RecyclerView.Adapter<CategoryAdapterNew.ViewHolder> {
    List<Categories> Categories;
    Context context;

    public CategoryAdapterNew(List<Categories> Categories, Context context) {
        this.Categories = Categories;
        this.context = context;
    }

    public void setCategories(List<Categories> Categories) {
        this.Categories = Categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.itemcountry, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(Categories.get(position).getStrCategory());
        Glide.with(context).load(Categories.get(position).getStrCategoryThumb()).placeholder(R.drawable.foodplaceholder).into(holder.image);
    }

    public void updatedata(List<Categories> Categories) {
        this.Categories = Categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return Categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameOfCountry);
            image = itemView.findViewById(R.id.circleImage);


        }
    }
}
