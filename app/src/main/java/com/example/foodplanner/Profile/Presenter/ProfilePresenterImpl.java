package com.example.foodplanner.Profile.Presenter;


import android.util.Log;
import android.util.Pair;

import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Profile.View.ProfileInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenterImpl implements ProfilePresenter, FireBaseCallback {
    ReposateryImpl reposatery;
    ProfileInterface profileInterface;
    CompositeDisposable compositeDisposable;
    private static  ProfilePresenterImpl instance = null;


    public ProfilePresenterImpl(ReposateryImpl reposatery, ProfileInterface profileInterface) {
        this.reposatery = reposatery;
        this.profileInterface = profileInterface;
        compositeDisposable = new CompositeDisposable();
        this.profileInterface.setEmail(getEmail());

        getMealWithDay();
    }


    public String getEmail() {
        return reposatery.getFireBaseUser().getEmail();
    }

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

    @Override
    public void logout() {
        reposatery.signOut();
    }

    @Override
    public void delete(MealWithDay mealWithDay) {
        compositeDisposable.add(reposatery.deleteMealPlan(mealWithDay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
//                    profileInterface.deleteed(mealWithDay);
                }, e -> {
                    profileInterface.showErrorMsg(e.getMessage());
                }));
    }

    @Override
    public void setMealWithDay(MealWithDay mealWithDays) {

    }

    @Override
    public void backupAllMeals(FireBaseCallback callback) {
        compositeDisposable.add(Flowable.zip(
                reposatery.getFavMeals(reposatery.getFireBaseUser().getUid()),
                reposatery.getMealPlan(reposatery.getFireBaseUser().getUid()),
                (v,p) -> {
                    return new Pair(v, p);
                }
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            e -> reposatery.backupAllMeals((ArrayList<Meal>) e.first,
                    (ArrayList<MealWithDay>) e.second,
                    callback)
        ));

    }

    @Override
    public void downloadAll(FireBaseCallback callback) {
        reposatery.downloadFavouriteMeals(callback,this);
        reposatery.downloadPlanMeals(callback,this);

    }

    @Override
    public void restoreAllFavouriteMeals(List<Meal> meals) {
        compositeDisposable.add(reposatery.onSuccessDownloadFavouriteMeals(meals).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                () -> profileInterface.showSuccessMsg("Downloaded Successfully"), e -> profileInterface.showErrorMsg(e.getMessage())
        ));
    }

    @Override
    public void restoreAllPlanMeals(List<MealWithDay> meals) {
        compositeDisposable.add(reposatery.onSuccessDownloadPlanMeals(meals).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                () -> profileInterface.showSuccessMsg("Downloaded Successfully"), e -> profileInterface.showErrorMsg(e.getMessage())
        ));
    }


    public String getUserId() {
        return reposatery.getFireBaseUser().getUid();
    }


    void getMealWithDay() {
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "1")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(

                        e -> {
                            Log.d("said", "said: " + e.size());
                            profileInterface.setDay1(e);
                        },
                        e -> profileInterface.showErrorMsg(e.getMessage())
                ));
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "2")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        e -> profileInterface.setDay2(e),
                        e -> profileInterface.showErrorMsg(e.getMessage())
                ));
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "3")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        e -> profileInterface.setDay3(e),
                        e -> profileInterface.showErrorMsg(e.getMessage())
                ));
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "4")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        e -> profileInterface.setDay4(e),
                        e -> profileInterface.showErrorMsg(e.getMessage())
                ));
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "5")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        e -> profileInterface.setDay5(e),
                        e -> profileInterface.showErrorMsg(e.getMessage())
                ));
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "6")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        e -> profileInterface.setDay6(e),
                        e -> profileInterface.showErrorMsg(e.getMessage())
                ));
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "7")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        e -> profileInterface.setDay7(e),
                        e -> profileInterface.showErrorMsg(e.getMessage())
                ));

    }

    @Override
    public void onSuccess(String msg) {
        profileInterface.showSuccessMsg(msg);
        profileInterface.finish();

    }

    @Override
    public void onFailure(String errorMsg) {
        profileInterface.showErrorMsg(errorMsg);
    }
}
