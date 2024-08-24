package com.example.foodplanner.Network.Base;

import androidx.annotation.NonNull;

import com.example.foodplanner.Model.Categoryresponse;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.MealsResponse;
import com.example.foodplanner.Network.CategoryCallback;
import com.example.foodplanner.Network.IngredientCallback;
import com.example.foodplanner.Network.MealsByFierstLetterCallBack;
import com.example.foodplanner.Network.MealsCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSourceImpl implements RemoteDataSource {
    private static final String BaseUrl = "https://www.themealdb.com/api/json/v1/1/";
    private static RemoteDataSourceImpl clinet = null;
    Retrofit retrofit;
    InterfaceRetrofit interfaceRetrofit;

    private RemoteDataSourceImpl() {
        retrofit = new Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        interfaceRetrofit = retrofit.create(InterfaceRetrofit.class);
    }

    public static RemoteDataSourceImpl getInstance() {
        if (clinet == null) {
            clinet = new RemoteDataSourceImpl();
        }
        return clinet;
    }

    @Override
    public void makeRandomMealsCall(MealsCallBack networkCallback) {
        interfaceRetrofit.getRandomMeals().enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful()) {
                    networkCallback.onSuccessMeals(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                networkCallback.onFailureMeals(t.getMessage());
            }

        });
    }

    @Override
    public void makeCategoryCall(CategoryCallback networkCallback) {
        interfaceRetrofit.getAllCategores().enqueue(new Callback<Categoryresponse>() {
            @Override
            public void onResponse(Call<Categoryresponse> call, Response<Categoryresponse> response) {
                if (response.isSuccessful()) {
                    networkCallback.onSuccessCategory(response.body().getCategories());
                }
            }

            @Override
            public void onFailure(Call<Categoryresponse> call, Throwable throwable) {
                networkCallback.onFailureCategory(throwable.getMessage());
            }
        });
    }

    @Override
    public void makeIngredientsCall(IngredientCallback ingredientCallback) {
        interfaceRetrofit.getAllIngredients().enqueue(new Callback<IngredientResponse>() {

            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if (response.isSuccessful()) {
                    ingredientCallback.onSuccessIngredient(response.body().getIngredients());
                }
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                ingredientCallback.onFailureIngredient(t.getMessage());
            }
        });
    }

    @Override
    public void makeMealsByCategoryCall(MealsCallBack mealsCallBack, String category) {
        interfaceRetrofit.getMealsByCategory(category).enqueue(new Callback<MealsResponse>() {

            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {

                if (response.isSuccessful()) {
                    mealsCallBack.onSuccessMeals(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {

                mealsCallBack.onFailureMeals(t.getMessage());
            }
        });
    }

    @Override
    public void makeMealsByCountryCall(MealsCallBack mealsCallBack, String country) {
        interfaceRetrofit.getMealsByCountry(country).enqueue(new Callback<MealsResponse>() {

            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {

                if (response.isSuccessful()) {
                    mealsCallBack.onSuccessMeals(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {

                mealsCallBack.onFailureMeals(t.getMessage());
            }
        });
    }

    @Override
    public void makeMealsByIngredientCall(MealsCallBack mealsCallBack, String ingredient) {
        interfaceRetrofit.getMealsByIngredient(ingredient).enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful()) {
                    mealsCallBack.onSuccessMeals(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {

                mealsCallBack.onFailureMeals(t.getMessage());
            }
        });


    }

    @Override
    public void makeMealsByIdCall(MealsCallBack mealsCallBack, String id) {
        interfaceRetrofit.getMealById(id).enqueue(new Callback<MealsResponse>() {

            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {

                if (response.isSuccessful()) {
                    mealsCallBack.onSuccessMeals(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {

                mealsCallBack.onFailureMeals(t.getMessage());
            }
        });
    }

    @Override
    public void makeMealByNameCall(MealsCallBack mealsCallBack, String name) {
        interfaceRetrofit.getMealByNmae(name).enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(@NonNull Call<MealsResponse> call, @NonNull Response<MealsResponse> response) {

                if (response.isSuccessful()) {
                    mealsCallBack.onSuccessMeals(response.body().getMeals());
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {

                mealsCallBack.onFailureMeals(t.getMessage());
            }
        });
    }
}


