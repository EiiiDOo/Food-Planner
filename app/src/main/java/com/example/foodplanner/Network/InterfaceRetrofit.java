package com.example.foodplanner.Network;

import com.example.foodplanner.Model.Categoryresponse;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.MealsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceRetrofit {
    @GET("categories.php")
    Call<Categoryresponse> getAllCategores();
    @GET("list.php?i=list")
    Call<IngredientResponse> getAllIngredients();
    @GET("filter.php")
    Call<MealsResponse> getMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    Call<MealsResponse> getMealsByIngredient(@Query("i") String category);
    @GET("filter.php")
    Call<MealsResponse> getMealsByCountry(@Query("a") String category);
    @GET("lookup.php")
    Call<MealsResponse> getMealById(@Query("i") String category);
    @GET("search.php")
    Call<MealsResponse> getMealByFirstLetter(@Query("f") String ch);
    @GET("random.php")
    Call<MealsResponse> getRandomMeals();
}
