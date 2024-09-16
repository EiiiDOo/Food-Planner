package com.example.foodplanner.Profile.View;

import android.content.Context;
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
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.R;

import java.util.List;

public class PlanAdaoter extends RecyclerView.Adapter<PlanAdaoter.Viewholder> {
    List<MealWithDay> MealWithDay;
    Context context;
    ListenerProfile listenerProfile;

    public PlanAdaoter(List<MealWithDay> MealWithDay, Context context, ListenerProfile listenerProfile) {
        this.listenerProfile = listenerProfile;
        this.MealWithDay = MealWithDay;
        this.context = context;
    }

    public void setMealWithDay(List<MealWithDay> MealWithDay) {
        this.MealWithDay = MealWithDay;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new Viewholder(inflater.inflate(R.layout.mealforplan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.title.setText(MealWithDay.get(position). getStrMeal());
        Glide.with(context).load(MealWithDay.get(position).getStrMealThumb()).into(holder.imageView);
        holder.button.setOnClickListener(v -> {
            ProfileFragmentDirections.ActionNavigationProfileToDetailsFragment action =
                    ProfileFragmentDirections.actionNavigationProfileToDetailsFragment(MealWithDay.get(position).getIdMeal(),
                        MealWithDay.get(position).transferToMeal(MealWithDay.get(position)));
            Navigation.findNavController(holder.itemView).navigate(action);
        });
        holder.delete.setOnClickListener(v -> {
            listenerProfile.delete(MealWithDay.get(position));
        });

    }

    public void updatedata(List<MealWithDay> MealWithDay) {
        this.MealWithDay = MealWithDay;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return MealWithDay.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        Button button;
        ImageButton delete;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.nameOfDailyMeal);
            imageView = itemView.findViewById(R.id.imageDaily);
            button = itemView.findViewById(R.id.btnfromMealByToDetails);
            delete = itemView.findViewById(R.id.btnCancle);
        }
    }
}
