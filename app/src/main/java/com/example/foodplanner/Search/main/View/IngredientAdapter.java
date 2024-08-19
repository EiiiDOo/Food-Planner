package com.example.foodplanner.Search.main.View;

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
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {
    List<Ingredients> ingredients;
    Context context;
    public IngredientAdapter(List<Ingredients>ingredients, Context context){
        this.ingredients = ingredients;
        this.context = context;
    }
    public void setCategories(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.rowrandom,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(ingredients.get(position). getStrIngredient());
        Glide.with(context).load("https://www.themealdb.com/images/ingredients/" + ingredients.get(position).getStrIngredient() + "-Small.png").placeholder(R.drawable.egypt).into(holder.imageView);
        holder.button.setOnClickListener(v -> {
            SearchFragmentDirections.ActionNavigationSearchToSearchByFragment action =
                    SearchFragmentDirections.actionNavigationSearchToSearchByFragment(
                            new TypeSearch(ingredients.get(position).getStrIngredient(), TypeSearch.Type.INGREDIENTS)
                    );
            Navigation.findNavController(holder.itemView).navigate(action);
        });
    }
public void updatedata(List<Ingredients> ingredients) {
    this.ingredients = ingredients;
    notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        if(ingredients == null){
            return 0;
        }
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.imageDaily);
            button = itemView.findViewById(R.id.button);
        }
    }
}