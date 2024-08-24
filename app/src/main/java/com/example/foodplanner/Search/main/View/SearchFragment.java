package com.example.foodplanner.Search.main.View;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.main.Presenter.SearchPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchInterfaceView {
    public static List<Ingredients> ingredients = new ArrayList<>();
    public static List<Categories> categories = new ArrayList<>();
    RecyclerView rvCountry, rvCategory, rvIngredient, rvMeal;
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

        new SearchPresenterImpl(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this.getContext())), this);
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
        SearchFragment.categories = categories;
        categoryAdapter.updatedata(categories);
    }

    @Override
    public void showIngredient(List<Ingredients> ingredients) {
        SearchFragment.ingredients = ingredients;
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


}