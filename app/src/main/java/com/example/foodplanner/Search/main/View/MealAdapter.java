package com.example.foodplanner.Search.main.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.NoInternetDialog;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.R;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.Viewholder> {
    List<Meal> Meal;
    Context context;
    FragmentManager fm;

    public MealAdapter(List<Meal> Meal, Context context, FragmentManager fm) {
        this.Meal = Meal;
        this.context = context;
        this.fm = fm;
    }

    public void setMeal(List<Meal> Meal) {
        this.Meal = Meal;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new Viewholder(inflater.inflate(R.layout.smallsmallmeal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.name.setText(Meal.get(position).getStrMeal());
        Glide.with(context).load(Meal.get(position).getStrMealThumb()).into(holder.image);
        holder.button.setOnClickListener(v -> {
            if (SearchFragment.isInternetAvailable) {
                SearchFragmentDirections.ActionNavigationSearchToSearchByFragment action =
                        SearchFragmentDirections.actionNavigationSearchToSearchByFragment(
                                new TypeSearch(Meal.get(position).getStrMeal(), TypeSearch.Type.MealsByNmae)
                        );
                Navigation.findNavController(holder.itemView).navigate(action);
            }else {
                NoInternetDialog d = new NoInternetDialog();
                d.show(fm, "No Internet");
            }

        });
    }

    public void updatedata(List<Meal> Meal) {
        this.Meal = Meal;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return Meal.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        Button button;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameOfMealIntosearch);
            image = itemView.findViewById(R.id.imageMealIntoSearch);
            button = itemView.findViewById(R.id.btnToSearchByMealFromSearch);

        }
    }
}
