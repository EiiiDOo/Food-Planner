package com.example.foodplanner.Search.Searchby.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.Network.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Searchby.Presenter.SearchByPresenter;
import com.example.foodplanner.Search.Searchby.Presenter.SearchByPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchByFragment extends Fragment implements SearchByView {
    String nameOfsearchBy;
    RecyclerView recyclerView;
    MealsAdapter adapter;
    SearchView searchView;
    SearchByPresenter searchByPresenter;
    TypeSearch  typeSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        typeSearch = SearchByFragmentArgs.fromBundle(getArguments()).getType();
        nameOfsearchBy = typeSearch.getParam();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.countryRecyclerView);
        searchView = view.findViewById(R.id.searchView);
        adapter = new MealsAdapter(new ArrayList<>(), view.getContext());
        searchView.clearFocus();
        searchView.setQuery(nameOfsearchBy, false);
        searchByPresenter = new SearchByPresenterImpl( ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance()), this);
        switch (typeSearch.getType()) {
            case COUNTRIES:
                searchByPresenter.fetchMealsByCountry(nameOfsearchBy);
                break;
            case INGREDIENTS:
                searchByPresenter.fetchMealsByIngredient(nameOfsearchBy);
                break;
            case CATEGORIES:
                searchByPresenter.fetchMealsByCategory(nameOfsearchBy);
                break;
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_searchby, container, false);
    }

    @Override
    public void showMeal(List<Meal> meals) {
        adapter.updatedata(meals);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showErrorMsg(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

    }
}