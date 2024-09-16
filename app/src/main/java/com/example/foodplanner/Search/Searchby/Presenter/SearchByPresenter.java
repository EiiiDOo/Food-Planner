package com.example.foodplanner.Search.Searchby.Presenter;

import com.example.foodplanner.Model.TypeSearch;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface SearchByPresenter {
    void choseType (TypeSearch.Type type);
    Single<List<String>> autoCompleteIngredient(String query);
    Single<String> autoCompleteCategory(String query);

}
