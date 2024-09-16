package com.example.foodplanner.Search.main.View;

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

import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.NoInternetDialog;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    List<Country> countryList;
    Context context;
    FragmentManager fm;
    public CountryAdapter(List<Country>countryList, Context context, FragmentManager fm) {
        this.countryList = countryList;
        this.context = context;
        this.fm = fm;
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
            if (SearchFragment.isInternetAvailable) {
                SearchFragmentDirections.ActionNavigationSearchToSearchByFragment action =
                        SearchFragmentDirections.actionNavigationSearchToSearchByFragment(
                                new TypeSearch(countryList.get(position).getCountryName(), TypeSearch.Type.COUNTRIES)
                        );
                Navigation.findNavController(holder.itemView).navigate(action);
            }else {
                NoInternetDialog d = new NoInternetDialog();
                d.show(fm, "No Internet");
            }

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
