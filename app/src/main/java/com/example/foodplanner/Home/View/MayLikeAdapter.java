package com.example.foodplanner.Home.View;

import android.content.Context;
import android.util.Log;
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
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.NoInternetDialog;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Searchby.View.SearchByFragmentDirections;

import java.util.List;

public class MayLikeAdapter extends RecyclerView.Adapter<MayLikeAdapter.MealView> {
    List<Meal> Meal;
    Context context;
    FragmentManager fm;

    public MayLikeAdapter(List<Meal> Meal, Context context, FragmentManager fm) {
        this.Meal = Meal;
        this.context = context;
        this.fm = fm;
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
    Glide.with(context).load(Meal.get(position).getStrMealThumb()).override(190,160).into(holder.image);
    holder.button.setOnClickListener(v -> {
        if (HomeFragment.internetFlag){
            HomeFragmentDirections.ActionNavigationHomeToDetailsFragment action =
                    HomeFragmentDirections.actionNavigationHomeToDetailsFragment(Meal.get(position).getIdMeal(), null);
            Navigation.findNavController(holder.itemView).navigate(action);
        }else {
            NoInternetDialog d = new NoInternetDialog();
            d.show(fm, "dialog");
        }

    });
        Log.d("Counter", "onBindViewHolder: "+position);
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
        Button button;
        public MealView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameOfDailyMeal);
            image = itemView.findViewById(R.id.imageDaily);
            button = itemView.findViewById(R.id.btnfromMealByToDetails);
        }
    }
}
