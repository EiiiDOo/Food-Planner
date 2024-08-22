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

import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    List<Country> countryList;
    Context context;
    public CountryAdapter(List<Country>countryList, Context context){
        this.countryList = countryList;
        this.context = context;
    }
    public void setCountries(List<Country> Countries) {
        this.countryList = Countries;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.itemcountry,parent,false));
    }
    public void updatedata(List<Country> countryList) {
        this.countryList = countryList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(countryList.get(position).getImageResourceId());
        holder.button.setOnClickListener(v -> {
            SearchFragmentDirections.ActionNavigationSearchToSearchByFragment action =
                    SearchFragmentDirections.actionNavigationSearchToSearchByFragment(
                            new TypeSearch(countryList.get(position).getCountryName(), TypeSearch.Type.COUNTRIES)
                    );
            Navigation.findNavController(holder.itemView).navigate(action);
        });

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.circleImage);
            button = itemView.findViewById(R.id.btnTosearchBycountry);
        }
    }
}
