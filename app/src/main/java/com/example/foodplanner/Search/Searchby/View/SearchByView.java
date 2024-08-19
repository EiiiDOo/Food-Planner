package com.example.foodplanner.Search.Searchby.View;

import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface SearchByView {
    public void showMeal(List<Meal> meals);
    public void showErrorMsg(String error);
}
