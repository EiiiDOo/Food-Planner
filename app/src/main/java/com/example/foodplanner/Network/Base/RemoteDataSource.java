package com.example.foodplanner.Network.Base;


import com.example.foodplanner.Model.Categoryresponse;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.MealsResponse;

import io.reactivex.rxjava3.core.Observable;


public interface RemoteDataSource {
    Observable<MealsResponse> makeRandomMealsCall( );

    Observable<Categoryresponse> makeCategoryCall();

    Observable<IngredientResponse> makeIngredientsCall( );

    Observable<MealsResponse> makeMealsByCategoryCall(String category);

    Observable<MealsResponse> makeMealsByCountryCall(String country);

    Observable<MealsResponse> makeMealsByIngredientCall(String ingredient);

    Observable<MealsResponse> makeMealsByIdCall(String id);

    Observable<MealsResponse> makeMealByNameCall( String name);
}
