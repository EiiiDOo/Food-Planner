package com.example.foodplanner.Home.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.R;

import java.util.List;

public class CountryAdapterNew extends RecyclerView.Adapter<CountryAdapterNew.CountryViewHolder> {
    List<Country> Country;
    Context context;

    public CountryAdapterNew(List<Country> Country, Context context) {
        this.Country = Country;
        this.context = context;
    }

    public void setCountry(List<Country> Country) {
        this.Country = Country;
    }

    public void updatedata(List<Country> Country) {
        this.Country = Country;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CountryViewHolder(inflater.inflate(R.layout.itemcountry, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.title.setText(Country.get(position).getCountryName());
        Glide.with(context).load(Country.get(position).getImageResourceId()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return Country.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.nameOfCountry);
            image = itemView.findViewById(R.id.circleImage);

        }
    }
}
