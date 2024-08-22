package com.example.foodplanner.Search.main.View;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface SearchInterfaceView {
    public void showCountry(List<Country> countryList);
    public void showErrorMsg(String error);
    public void showCategory(List<Categories> categories);
    public void showIngredient(List<Ingredients> ingredients);
    void showMealsByName(List<Meal> Meals);

}
