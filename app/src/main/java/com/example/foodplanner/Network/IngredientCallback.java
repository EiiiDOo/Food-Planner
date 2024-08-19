package com.example.foodplanner.Network;

import com.example.foodplanner.Model.Categories;
import com.example.foodplanner.Model.Ingredients;

import java.util.List;

public interface IngredientCallback {
    public void onSuccessIngredient(List<Ingredients> ingredients);
    public void onFailureIngredient(String errorMsg);
}
