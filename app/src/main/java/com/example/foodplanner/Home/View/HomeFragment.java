package com.example.foodplanner.Home.View;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.AllCountries;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Home.Presenter.HomePresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeView {
    RecyclerView rvCountry, rvCategory, rvMayLike;
    CountryAdapterNew countryAdapterNew;
    CategoryAdapterNew categoryAdapterNew;
    MayLikeAdapter mayLikeAdapter;
    HomePresenterImpl homePresenter;
    ImageView dailyInspiration;
    TextView nameOfMeal, backGroundProgressPar;
    ProgressBar progressBar;
    Button btnToDailsfromrandomMeal;
    Meal random;
    final String TAG = "HomeFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dailyInspiration = view.findViewById(R.id.imageDaily);
        nameOfMeal = view.findViewById(R.id.nameOfDailyMeal);
        rvCountry = view.findViewById(R.id.rvCountry);
        rvCategory = view.findViewById(R.id.rvcategory);
        rvMayLike = view.findViewById(R.id.rvYouMightLike);
        backGroundProgressPar = view.findViewById(R.id.backProgrespar);
        progressBar = view.findViewById(R.id.progressBar2);
        btnToDailsfromrandomMeal = view.findViewById(R.id.btnFromRandomMealToDetails);
        countryAdapterNew = new CountryAdapterNew(AllCountries.getInstance().getAllCountries(), getContext());

        categoryAdapterNew = new CategoryAdapterNew(new ArrayList<>(), getContext());
        mayLikeAdapter = new MayLikeAdapter(new ArrayList<>(), getContext());
        rvCountry.setAdapter(countryAdapterNew);
        rvCategory.setAdapter(categoryAdapterNew);
        rvMayLike.setAdapter(mayLikeAdapter);
        homePresenter = new HomePresenterImpl(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance()), this);

        btnToDailsfromrandomMeal.setOnClickListener(v -> {
            HomeFragmentDirections.ActionNavigationHomeToDetailsFragment action =
                    HomeFragmentDirections.actionNavigationHomeToDetailsFragment(random.getIdMeal());
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public void showCountry() {

    }

    @Override
    public void showMeal(List<Meal> meals) {
        nameOfMeal.setText(meals.get(0).getStrMeal());
        random = meals.get(0);
        Glide.with(getContext())
                .load(meals.get(0)
                        .getStrMealThumb())
                .into(dailyInspiration);

        mayLikeAdapter.updatedata(meals);
    }

    @Override
    public void showMealByFirstLetter(List<Meal> meals) {

        mayLikeAdapter.updatedata(meals);
    }

    @Override
    public void showCategory(List<Categories> categories) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            backGroundProgressPar.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }, 1000);
        categoryAdapterNew.updatedata(categories);
    }

    @Override
    public void showErrorMsg(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

    }

}