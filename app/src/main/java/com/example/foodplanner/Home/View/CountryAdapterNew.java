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
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.NoInternetDialog;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.Profile.View.ProfileFragment;
import com.example.foodplanner.R;

import java.util.List;

public class CountryAdapterNew extends RecyclerView.Adapter<CountryAdapterNew.CountryViewHolder> {
    List<Country> Country;
    Context context;
    FragmentManager fm;

    public CountryAdapterNew(List<Country> Country, Context context, FragmentManager fm) {
        this.Country = Country;
        this.context = context;
        this.fm = fm;
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
        holder.button.setOnClickListener(v -> {
            if (HomeFragment.internetFlag) {
                HomeFragmentDirections.ActionNavigationHomeToSearchByFragment action =
                        HomeFragmentDirections.actionNavigationHomeToSearchByFragment(
                                new TypeSearch(Country.get(position).getCountryName(), TypeSearch.Type.COUNTRIES)
                        );
                Navigation.findNavController(holder.itemView).navigate(action);
            }else {
                NoInternetDialog d = new NoInternetDialog();
                d.show(fm, "dialog");
            }

        });
        Glide.with(context).load(Country.get(position).getImageResourceId()).into(holder.image);
        Log.d("Counter", "onBindViewHolder: "+position);
    }

    @Override
    public int getItemCount() {
        return Country.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        Button button;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btnTosearchBycountry);
            image = itemView.findViewById(R.id.circleImage);

        }
    }
}
