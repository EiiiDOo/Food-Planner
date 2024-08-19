package com.example.foodplanner.Network;

public interface RemoteDataSource {
     void makeRandomMealsCall(MealsCallBack networkCallback);
     void makeCategoryCall(CategoryCallback networkCallback);
     void makeIngredientsCall(IngredientCallback networkCallback);
     void makeMealByFirstLetter(MealsByFierstLetterCallBack byFierstLetterCallBack,String ch);
     void makeMealsByCategoryCall(MealsCallBack mealsCallBack,String category);
     void makeMealsByCountryCall(MealsCallBack mealsCallBack,String country);
     void makeMealsByIngredientCall(MealsCallBack mealsCallBack,String ingredient);
     void makeMealsByIdCall(MealsCallBack mealsCallBack,String id);
}
