package com.example.foodplanner.Network.Base;

import com.example.foodplanner.Model.Categoryresponse;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.MealsResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSourceImpl implements RemoteDataSource {
    private static final String BaseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static RemoteDataSourceImpl clinet = null;
    Retrofit retrofit;
    InterfaceRetrofit interfaceRetrofit;

    private RemoteDataSourceImpl() {
        retrofit = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();
        interfaceRetrofit = retrofit.create(InterfaceRetrofit.class);
    }

    public static RemoteDataSourceImpl getInstance() {
        if (clinet == null) {
            clinet = new RemoteDataSourceImpl();
        }
        return clinet;
    }

    @Override
    public Observable<MealsResponse> makeRandomMealsCall( ) {
        Observable<MealsResponse> call = interfaceRetrofit.getRandomMeals();
        return call;
    }

    @Override
    public Observable<Categoryresponse> makeCategoryCall() {

        Observable<Categoryresponse> call = interfaceRetrofit.getAllCategores();
        return call;
    }

    @Override
    public Observable<IngredientResponse> makeIngredientsCall() {

        Observable<IngredientResponse> call = interfaceRetrofit.getAllIngredients();
        return call;
    }

    @Override
    public Observable<MealsResponse> makeMealsByCategoryCall( String category) {

        Observable<MealsResponse> call = interfaceRetrofit.getMealsByCategory(category);
        return call;
    }

    @Override
    public Observable<MealsResponse> makeMealsByCountryCall(  String country) {

        Observable<MealsResponse> call = interfaceRetrofit.getMealsByCountry(country);
        return call;
    }

    @Override
    public Observable<MealsResponse> makeMealsByIngredientCall( String ingredient) {

        Observable<MealsResponse> call = interfaceRetrofit.getMealsByIngredient(ingredient);
        return call;

    }

    @Override
    public Observable<MealsResponse> makeMealsByIdCall( String id) {

        Observable<MealsResponse> call = interfaceRetrofit.getMealById(id);
        return call;
    }

    @Override
    public Observable<MealsResponse> makeMealByNameCall(  String name) {

        Observable<MealsResponse> call = interfaceRetrofit.getMealByNmae(name);
        return call;

    }
}


