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
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<Categories> Categories;
    Context context;
    public CategoryAdapter(List<Categories>Categories, Context context){
        this.Categories = Categories;
        this.context = context;
    }
    public void setCategories(List<Categories> Categories) {
        this.Categories = Categories;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.rowrandom,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(Categories.get(position). getStrCategory());
        Glide.with(context).load(Categories.get(position).getStrCategoryThumb()).into(holder.imageView);
        holder.button.setOnClickListener(v -> {
            SearchFragmentDirections.ActionNavigationSearchToSearchByFragment action =
                    SearchFragmentDirections.actionNavigationSearchToSearchByFragment(
                            new TypeSearch(Categories.get(position).getStrCategory(), TypeSearch.Type.CATEGORIES)
                    );
            Navigation.findNavController(holder.itemView).navigate(action);
        });
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
