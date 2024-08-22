package com.example.foodplanner.Search.main.View;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.main.Presenter.SearchPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchInterfaceView {
    final static String TAG = "SearchFragment";

    RecyclerView rvCountry, rvCategory, rvIngredient,rvMeal;
    CountryAdapter countryAdapter;
    CategoryAdapter categoryAdapter;
    IngredientAdapter ingredientAdapter;
    MealAdapter mealAdapter;
    ProgressBar progressBar;
    TextView backGroundProgressBar;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.progressBar2);
        backGroundProgressBar = view.findViewById(R.id.backProgrespar);
        rvMeal = view.findViewById(R.id.rvMealsInSearchBy);
        rvCategory = view.findViewById(R.id.rvCategoryInSearchBy);
        rvCountry = view.findViewById(R.id.rvCountryInSearchBy);
        rvIngredient = view.findViewById(R.id.rvIngredientInSearchBy);

        mealAdapter = new MealAdapter(new ArrayList<>(), getContext());
        categoryAdapter = new CategoryAdapter(new ArrayList<>(), getContext());
        countryAdapter = new CountryAdapter(new ArrayList<>(), getContext());
        ingredientAdapter = new IngredientAdapter(new ArrayList<>(), getContext());

        rvMeal.setAdapter(mealAdapter);
        rvCategory.setAdapter(categoryAdapter);
        rvCountry.setAdapter(countryAdapter);
        rvIngredient.setAdapter(ingredientAdapter);

        SearchPresenterImpl searchPresenter = new SearchPresenterImpl(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance()), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void showCountry(List<Country> countryList) {
        countryAdapter.updatedata(countryList);
    }

    @Override
    public void showErrorMsg(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCategory(List<Categories> categories) {
        categoryAdapter.updatedata(categories);
    }

    @Override
    public void showIngredient(List<Ingredients> ingredients) {
        ingredientAdapter.updatedata(ingredients);
    }

    @Override
    public void showMealsByName(List<Meal> Meals) {
        mealAdapter.updatedata(Meals);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            backGroundProgressBar.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }, 1000);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}