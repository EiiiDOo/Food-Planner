package com.example.foodplanner.Favourite.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Searchby.View.SearchByFragmentDirections;

import java.util.List;

public class MealsAdapterFavourite extends RecyclerView.Adapter<MealsAdapterFavourite.ViewHolder> {
    List<Meal> meals;
    Context context;
    FavLestener favLestener;


    public MealsAdapterFavourite(List<Meal>meals, Context context, FavLestener favLestener) {
        this.meals = meals;
        this.context = context;
        this.favLestener = favLestener;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.mealforfavfragment, parent, false));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(meals.get(position). getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb()).into(holder.imageView);


        holder.button.setOnClickListener(v -> {
            FavouriteFragmentDirections.ActionNavigationFavouriteToDetailsFragment action =
                    FavouriteFragmentDirections.actionNavigationFavouriteToDetailsFragment(meals.get(position).getIdMeal(), meals.get(position));
            Navigation.findNavController(holder.itemView).navigate(action);
        });
        holder.delete.setOnClickListener(v -> {
            favLestener.delete(meals.get(position));
        });
    }
@SuppressLint("NotifyDataSetChanged")
public void updatedata(List<Meal> meals) {
    this.meals = meals;
    notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        Button button;
        ImageButton delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.nameOfDailyMeal);
            imageView = itemView.findViewById(R.id.imageDaily);
            button = itemView.findViewById(R.id.btnfromMealByToDetails);
            delete = itemView.findViewById(R.id.btnCancle);

        }
    }
}
