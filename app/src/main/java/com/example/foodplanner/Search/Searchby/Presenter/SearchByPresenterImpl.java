package com.example.foodplanner.Search.Searchby.Presenter;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Model.TypeSearch;
import com.example.foodplanner.Network.MealsCallBack;
import com.example.foodplanner.Search.Searchby.View.SearchByView;
import com.example.foodplanner.Search.main.View.SearchFragment;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
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
        if (Meals == null)
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
                searchByView.withTypingCountry()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> {
                            if (e.equals("null"))
                                searchByView.showErrorMsg("No Meals Found");
                            else
                                reposatery.fetchMealsByCountry(e, this);
                        });
                break;
            case INGREDIENTS:
                reposatery.fetchMealsByIngredient(typeSearch.getParam(), this);
                searchByView.getTitle("Ingredients");
                searchByView.withTypingIngredient()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> {
                            if (e.equals("null"))
                                searchByView.showErrorMsg("No Meals Found");
                            else
                                reposatery.fetchMealsByIngredient(e, this);
                        });
                break;
            case CATEGORIES:
                reposatery.fetchMealsByCategory(typeSearch.getParam(), this);
                searchByView.getTitle("Categories");
                searchByView.withTypingCategory()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> {
                            if (e.equals("null"))
                                searchByView.showErrorMsg("No Meals Found");
                            else
                                reposatery.fetchMealsByCategory(e, this);
                        });
                break;
            case MealsByNmae:
                reposatery.fetchMealsByName(typeSearch.getParam(), this);
                searchByView.getTitle("Meals");
                searchByView.withTypingMeal()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> {
                            if (e.equals("null"))
                                searchByView.showErrorMsg("No Meals Found");
                            else
                                reposatery.fetchMealsByName(e, this);
                        });
                break;
        }
    }

    @Override
    public Single<String> autoCompleteIngredient(String query) {

        return Observable.fromIterable(SearchFragment.ingredients)
                .map(Ingredients::getStrIngredient)
                .filter(country -> country.toLowerCase().startsWith(query.toLowerCase())).first("null");
    }

    @Override
    public Single<String> autoCompleteCategory(String query) {
        return Observable.fromIterable(SearchFragment.categories)
                .map(Categories::getStrCategory)
                .filter(country -> country.toLowerCase().startsWith(query.toLowerCase())).first("null");
    }

}
