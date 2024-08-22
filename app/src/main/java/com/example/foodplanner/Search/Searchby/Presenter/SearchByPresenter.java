package com.example.foodplanner.Search.Searchby.Presenter;

import com.example.foodplanner.Model.TypeSearch;

import io.reactivex.rxjava3.core.Single;

public interface SearchByPresenter {
    void choseType (TypeSearch.Type type);
    Single<String> autoCompleteIngredient(String query);
    Single<String> autoCompleteCategory(String query);

}
