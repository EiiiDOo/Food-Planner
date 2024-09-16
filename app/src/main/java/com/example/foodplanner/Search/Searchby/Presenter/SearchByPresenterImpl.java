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
                reposatery.fetchMealsByCountry(typeSearch.getParam()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> onSuccessMeals(e.getMeals()), e -> onFailureMeals(e.getMessage()));
                searchByView.getTitle("Countries");
                searchByView.withTypingCountry()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> {
                            if (e.equals("null"))
                                searchByView.showErrorMsg("No Meals Found");
                            else
                                reposatery.fetchMealsByCountry(e).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                                        .subscribe( l -> onSuccessMeals(l.getMeals()), w->onFailureMeals(w.getMessage()));
                        });
                break;
            case INGREDIENTS:
                reposatery.fetchMealsByIngredient(typeSearch.getParam())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> onSuccessMeals(e.getMeals()), e -> onFailureMeals(e.getMessage()));
                searchByView.getTitle("Ingredients");
                searchByView.withTypingIngredient()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMapIterable(e-> e)
                        .subscribe(e -> {

                                reposatery.fetchMealsByIngredient(e)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(l -> onSuccessMeals(l.getMeals()), w -> onFailureMeals(w.getMessage()));
                        });
                break;
            case CATEGORIES:
                reposatery.fetchMealsByCategory(typeSearch.getParam())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> onSuccessMeals(e.getMeals()), e -> onFailureMeals(e.getMessage()));
                searchByView.getTitle("Categories");
                searchByView.withTypingCategory()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> {
                            if (e.equals("null"))
                                searchByView.showErrorMsg("No Meals Found");
                            else
                                reposatery.fetchMealsByCategory(e)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(l -> onSuccessMeals(l.getMeals()), w -> onFailureMeals(w.getMessage()));
                        });
                break;
            case MealsByNmae:
                reposatery.fetchMealsByName(typeSearch.getParam())
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> onSuccessMeals(e.getMeals()), e -> onFailureMeals(e.getMessage()));
                searchByView.getTitle("Meals");
                searchByView.withTypingMeal()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(e -> {
                            if (e.equals("null"))
                                searchByView.showErrorMsg("No Meals Found");
                            else
                                reposatery.fetchMealsByName(e)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(l -> onSuccessMeals(l.getMeals()), w -> onFailureMeals(w.getMessage()));
                        });
                break;
        }
    }

    @Override
    public Single<List<String>> autoCompleteIngredient(String query) {

        return Observable.fromIterable(SearchFragment.ingredients)
                .map(Ingredients::getStrIngredient)
                .filter(country -> country.toLowerCase().contains(query.toLowerCase())).toList();
    }

    @Override
    public Single<String> autoCompleteCategory(String query) {
        return Observable.fromIterable(SearchFragment.categories)
                .map(Categories::getStrCategory)
                .filter(country -> country.toLowerCase().startsWith(query.toLowerCase())).first("null");
    }

}
