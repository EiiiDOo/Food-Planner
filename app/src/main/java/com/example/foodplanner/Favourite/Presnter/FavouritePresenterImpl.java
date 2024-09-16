package com.example.foodplanner.Favourite.Presnter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.foodplanner.Favourite.View.FavouriteInterface;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.Reposatery.ReposateryInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouritePresenterImpl implements favouritePresemterInterface {
    FavouriteInterface favouriteInterface;
    ReposateryInterface repo;
    String TAG = "FavouritePresenterImpl";
    CompositeDisposable compositeDisposable;

    public FavouritePresenterImpl(FavouriteInterface favouriteInterface, ReposateryInterface repo) {
        this.favouriteInterface = favouriteInterface;
        this.repo = repo;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getListOfFavMeal( ) {
        compositeDisposable.add(repo.getFavMeals(repo.getFireBaseUser().getUid())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(meals -> Log.d(TAG, "getListOfFavMeal before : "+meals.size()))
                .subscribe(e -> {
                            Log.d(TAG, "getListOfFavMeal:after "+e.size());
                            favouriteInterface.showFavMeals(e);
                        }
                        , e -> {
                            Log.d(TAG, "getListOfFavMeal: "+e.getMessage());
                            favouriteInterface.showErrorMsg(e.getMessage());
                        }));

    }

    @Override
    public String getUserId() {
        return repo.getFireBaseUser().getUid();
    }

    @Override
    public void deleteFromFav(Meal meal) {
        compositeDisposable.add(repo.deleteFavMeals(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    favouriteInterface.deletedSuccessfully(meal);
                    favouriteInterface.showSuccessMsg("Deleted Successfully");

                }, e -> favouriteInterface.showErrorMsg(e.getMessage())));
    }

}
