package com.example.foodplanner.Network.Base;

import com.example.foodplanner.Network.CategoryCallback;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsByFierstLetterCallBack;
import com.example.foodplanner.Network.MealsCallBack;

public interface RemoteDataSource {
    void makeRandomMealsCall(MealsCallBack networkCallback);

    void makeCategoryCall(CategoryCallback networkCallback);

    void makeIngredientsCall(IngredientCallback networkCallback);

    void makeMealsByCategoryCall(MealsCallBack mealsCallBack, String category);

    void makeMealsByCountryCall(MealsCallBack mealsCallBack, String country);

    void makeMealsByIngredientCall(MealsCallBack mealsCallBack, String ingredient);

    void makeMealsByIdCall(MealsCallBack mealsCallBack, String id);

    void makeMealByNameCall(MealsCallBack mealsCallBack, String name);
}
