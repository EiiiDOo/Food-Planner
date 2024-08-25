package com.example.foodplanner.Network.Base;



import com.example.foodplanner.Model.Categoryresponse;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.MealsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceRetrofit {
    @GET("categories.php")
    Observable<Categoryresponse> getAllCategores();
    @GET("list.php?i=list")
    Observable<IngredientResponse> getAllIngredients();
    @GET("filter.php")
    Observable<MealsResponse> getMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    Observable<MealsResponse> getMealsByIngredient(@Query("i") String category);
    @GET("filter.php")
    Observable<MealsResponse> getMealsByCountry(@Query("a") String category);
    @GET("lookup.php")
    Observable<MealsResponse> getMealById(@Query("i") String category);
    @GET("search.php")
    Observable<MealsResponse> getMealByFirstLetter(@Query("f") String ch);
    @GET("search.php")
    Observable<MealsResponse> getMealByNmae(@Query("s") String c);
    @GET("random.php")
    Observable<MealsResponse> getRandomMeals();
}
