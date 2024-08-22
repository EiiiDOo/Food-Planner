package com.example.foodplanner.Search.Searchby.Presenter;

import android.util.Log;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.Network.MealsCallBack;
import com.example.foodplanner.Search.Searchby.View.SearchByView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchByPresenterImpl implements SearchByPresenter, MealsCallBack {
    ReposateryImpl reposatery;
    SearchByView searchByView;
    TypeSearch typeSearch;

    public SearchByPresenterImpl(TypeSearch typeSearch, ReposateryImpl reposatery, SearchByView searchByView) {
        this.reposatery = reposatery;
        this.searchByView = searchByView;
        this.typeSearch = typeSearch;
        choseType(typeSearch.getType());

    }

    @Override
    public void onSuccessMeals(List<Meal> Meals) {
        if( Meals == null)
                searchByView.showErrorMsg("No Data Found");
            else
                searchByView.showMeal(Meals);
    }

    @Override
    public void onFailureMeals(String errorMsg) {

        searchByView.showErrorMsg(errorMsg);
    }

    @Override
    public void choseType(TypeSearch.Type type) {
        switch (type) {
            case COUNTRIES:
                reposatery.fetchMealsByCountry(typeSearch.getParam(), this);
                searchByView.getTitle("Countries");
                break;
            case INGREDIENTS:
                reposatery.fetchMealsByIngredient(typeSearch.getParam(), this);
                searchByView.getTitle("Ingredients");
                break;
            case CATEGORIES:
                reposatery.fetchMealsByCategory(typeSearch.getParam(), this);
                searchByView.getTitle("Categories");
                break;
            case MealsByNmae:
                reposatery.fetchMealsByName(typeSearch.getParam(), this);
                searchByView.getTitle("Meals");
                searchByView.withTyping()
                        .subscribe(
                                (e) -> {reposatery.fetchMealsByName(e, this);}
                        );
                break;
        }
    }

}
