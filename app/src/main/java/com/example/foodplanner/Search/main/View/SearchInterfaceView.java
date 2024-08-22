package com.example.foodplanner.Search.main.View;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredients;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface SearchInterfaceView {
     void showCountry(List<Country> countryList);
     void showErrorMsg(String error);
     void showCategory(List<Categories> categories);
     void showIngredient(List<Ingredients> ingredients);
    void showMealsByName(List<Meal> Meals);

}
