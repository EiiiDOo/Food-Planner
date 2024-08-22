package com.example.foodplanner.Details.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.R;

import java.util.List;

public class IngredientAdapterdetails extends RecyclerView.Adapter<IngredientAdapterdetails.ViewHolder> {
    List<Ingredients> Ingredients;
    Context context;

    public IngredientAdapterdetails(List<Ingredients> Ingredients, Context context) {
        this.Ingredients = Ingredients;
        this.context = context;
    }

    public void setIngredients(List<Ingredients> Ingredients) {
        this.Ingredients = Ingredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.rowcategoryoringredient, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(Ingredients.get(position). getStrIngredient());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + Ingredients.get(position).getStrIngredient() + "-Small.png").placeholder(R.drawable.foodplaceholder).into(holder.imageView);

    }

    public void updatedata(List<Ingredients> Ingredients) {
        this.Ingredients = Ingredients;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return Ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.nameOfCategory);
            imageView = itemView.findViewById(R.id.circleImageOfCategory);

        }
    }
}
