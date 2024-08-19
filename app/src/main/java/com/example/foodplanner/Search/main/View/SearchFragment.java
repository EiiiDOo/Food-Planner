package com.example.foodplanner.Search.main.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.main.Presenter.SearchPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchInterfaceView {

    RecyclerView rvCountry, rvCategory, rvIngredient;
    CountryAdapter countryAdapter;
    CategoryAdapter categoryAdapter;
    IngredientAdapter ingredientAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCountry = view.findViewById(R.id.rvcategory);
        rvCategory = view.findViewById(R.id.rvcategory);
        rvIngredient = view.findViewById(R.id.rvCountry);

        countryAdapter = new CountryAdapter(new ArrayList<>(), getContext());
        categoryAdapter = new CategoryAdapter(new ArrayList<>(), getContext());
        ingredientAdapter = new IngredientAdapter(new ArrayList<>(), getContext());

        rvCountry.setAdapter(countryAdapter);
        rvCategory.setAdapter(categoryAdapter);
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

}