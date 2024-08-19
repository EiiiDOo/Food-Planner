package com.example.foodplanner.Home.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.R;

import java.util.List;

public class MayLikeAdapter extends RecyclerView.Adapter<MayLikeAdapter.MealView> {
    List<Meal> Meal;
    Context context;

    public MayLikeAdapter(List<Meal> Meal, Context context) {
        this.Meal = Meal;
        this.context = context;
    }

    public void setMeal(List<Meal> Meal) {
        this.Meal = Meal;
    }

    @NonNull
    @Override
    public MealView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MealView(inflater.inflate(R.layout.smallmeal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealView holder, int position) {
    holder.name.setText(Meal.get(position).getStrMeal());
    Glide.with(context).load(Meal.get(position).getStrMealThumb()).placeholder(R.drawable.foodplaceholder).override(190,160).into(holder.image);

    }

    public void updatedata(List<Meal> Meal) {
        this.Meal = Meal;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return Meal.size();
    }

    public class MealView extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public MealView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameOfDailyMeal);
            image = itemView.findViewById(R.id.imageDaily);

        }
    }
}
