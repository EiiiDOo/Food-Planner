package com.example.foodplanner.Search.Searchby.View;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public interface SearchByView {
     void showMeal(List<Meal> meals);
     void showErrorMsg(String error);
    void getTitle(String title);
    Observable<String> withTypingMeal();
    Observable<String> withTypingCountry();
    Observable<String> withTypingIngredient();
    Observable<String> withTypingCategory();
}
