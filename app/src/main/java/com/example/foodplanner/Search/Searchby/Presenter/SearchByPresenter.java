package com.example.foodplanner.Search.Searchby.Presenter;

import com.example.foodplanner.Network.MealsCallBack;

public interface SearchByPresenter {
    void fetchMealsByCategory(String category);
    void fetchMealsByIngredient(String ingredient);
    void fetchMealsByCountry(String category);
}
