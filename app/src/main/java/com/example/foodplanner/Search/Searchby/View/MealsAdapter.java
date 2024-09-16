package com.example.foodplanner.Search.Searchby.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.NoInternetDialog;
import com.example.foodplanner.R;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.ViewHolder> {
    List<Meal> meals;
    Context context;
    FragmentManager fm;
    public MealsAdapter(List<Meal>meals, Context context, FragmentManager fm) {
        this.meals = meals;
        this.context = context;
        this.fm = fm;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.smallmeal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(meals.get(position). getStrMeal());
        Glide.with(context).load(meals.get(position).getStrMealThumb()).into(holder.imageView);
        holder.button.setOnClickListener(v -> {
            if (SearchByFragment.isInternetAvailable) {
                SearchByFragmentDirections.ActionSearchByFragmentToDetailsFragment action =
                        SearchByFragmentDirections.actionSearchByFragmentToDetailsFragment(meals.get(position).getIdMeal(),null);
                Navigation.findNavController(holder.itemView).navigate(action);
            }else {
                NoInternetDialog d = new NoInternetDialog();
                d.show(fm, "No Internet");
            }

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.nameOfDailyMeal);
            imageView = itemView.findViewById(R.id.imageDaily);
            button = itemView.findViewById(R.id.btnfromMealByToDetails);

        }
    }
}
