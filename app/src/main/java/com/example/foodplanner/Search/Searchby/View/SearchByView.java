package com.example.foodplanner.Search.Searchby.View;

import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface SearchByView {
    public void showMeal(List<Meal> meals);
    public void showErrorMsg(String error);
    void getTitle(String title);
    Observable<String> withTyping();
}
