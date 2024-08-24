package com.example.foodplanner.Profile.Presenter;

import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Profile.View.ProfileInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenterImpl implements ProfilePresenter {
    ReposateryImpl reposatery;
    ProfileInterface profileInterface;
    CompositeDisposable compositeDisposable;

    public ProfilePresenterImpl(ReposateryImpl reposatery, ProfileInterface profileInterface) {
        this.reposatery = reposatery;
        this.profileInterface = profileInterface;
        compositeDisposable = new CompositeDisposable();
        this.profileInterface.setEmail(getEmail());
        getMealWithDay1();
        getMealWithDay2();
        getMealWithDay3();
        getMealWithDay4();
        getMealWithDay5();
        getMealWithDay6();
        getMealWithDay7();
    }
    public String getEmail(){return reposatery.getFireBaseUser().getEmail();}

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

    @Override
    public void delete(MealWithDay mealWithDay) {
        compositeDisposable.add(reposatery.deleteMealPlan(mealWithDay)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {profileInterface.deleteed(mealWithDay);}, e -> {profileInterface.showErrorMsg(e.getMessage());}));
    }

    @Override
    public void setMealWithDay(MealWithDay mealWithDays) {

    }

    public String getUserId(){return reposatery.getFireBaseUser().getUid();}


    void getMealWithDay1() {
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealWithDays -> {profileInterface.setDay1(mealWithDays);}, e -> {profileInterface.showErrorMsg(e.getMessage());}));
    }

    void getMealWithDay2() {
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealWithDays -> {profileInterface.setDay2(mealWithDays);}, e -> {profileInterface.showErrorMsg(e.getMessage());}));
    }

    void getMealWithDay3() {
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "3")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealWithDays -> {profileInterface.setDay3(mealWithDays);}, e -> {profileInterface.showErrorMsg(e.getMessage());}));
    }
    void getMealWithDay4() {
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "4")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealWithDays -> {profileInterface.setDay4(mealWithDays);}, e -> {profileInterface.showErrorMsg(e.getMessage());}));
    }
    void getMealWithDay5() {
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealWithDays -> {profileInterface.setDay5(mealWithDays);}, e -> {profileInterface.showErrorMsg(e.getMessage());}));
    }
    void getMealWithDay6() {
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "6")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealWithDays -> {profileInterface.setDay6(mealWithDays);}, e -> {profileInterface.showErrorMsg(e.getMessage());}));
    }
    void getMealWithDay7() {
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "7")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealWithDays -> {profileInterface.setDay7(mealWithDays);}, e -> {profileInterface.showErrorMsg(e.getMessage());}));
    }


}
