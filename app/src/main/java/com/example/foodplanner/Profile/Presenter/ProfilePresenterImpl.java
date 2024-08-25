package com.example.foodplanner.Profile.Presenter;


import com.example.foodplanner.FireBase.FireBaseCallback;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Profile.View.ProfileInterface;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenterImpl implements ProfilePresenter , FireBaseCallback {
    ReposateryImpl reposatery;
    ProfileInterface profileInterface;
    CompositeDisposable compositeDisposable;


    public ProfilePresenterImpl( ReposateryImpl reposatery, ProfileInterface profileInterface) {
        this.reposatery = reposatery;
        this.profileInterface = profileInterface;
        compositeDisposable = new CompositeDisposable();
        this.profileInterface.setEmail(getEmail());


        getMealWithDay();
    }
    public String getEmail(){return reposatery.getFireBaseUser().getEmail();}

    @Override
    public void clear() {
        compositeDisposable.clear();
    }

    @Override
    public void logout() {
        reposatery.signOut(this);
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

    @Override
    public void getMealPlanWithIdday() {

    }

    public String getUserId(){return reposatery.getFireBaseUser().getUid();}


    void getMealWithDay() {
        compositeDisposable.add(reposatery.getMealPlan(getUserId(), "1")
                .subscribeOn(Schedulers.io())
                        .observeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        e -> profileInterface.setDay1(e),
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


//        compositeDisposable.add(reposatery.getMealPlan(getUserId())
//                .subscribeOn(Schedulers.io())
//                        .doOnNext(e -> Log.d("ProfilePresenterImpl", "getMealWithDay: "+e.size()))
//                .flatMap(e->  Flowable.fromIterable(e))
//                        .doOnNext(e -> Log.d("ProfilePresenterImpl", "getMealWithDay: before subscribe"+e.getDay()))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(mealWithDays -> {
//                    Log.d("ProfileFragment", "getMealWithDay: emitted"+mealWithDays.getDay());
//                    switch (mealWithDays.getDay()) {
//                        case "1":
//                            Log.d("ProfilePresenterImpl", "getMealWithDay:1 "+mealWithDays.getDay());
//                            profileInterface.setDay1(mealWithDays);
//                            break;
//                        case "2":
//                            Log.d("ProfilePresenterImpl", "getMealWithDay:2 "+mealWithDays.getDay());
//                            profileInterface.setDay2(mealWithDays);
//                            break;
//                        case "3":
//                            Log.d("ProfilePresenterImpl", "getMealWithDay:3 "+mealWithDays.getDay());
//                            profileInterface.setDay3(mealWithDays);
//                            break;
//                        case "4":
//                            Log.d("ProfilePresenterImpl", "getMealWithDay:4 "+mealWithDays.getDay());
//                            profileInterface.setDay4(mealWithDays);
//                            break;
//                        case "5":
//                            Log.d("ProfilePresenterImpl", "getMealWithDay:5 "+mealWithDays.getDay());
//                            profileInterface.setDay5(mealWithDays);
//                            break;
//                        case "6":
//                            Log.d("ProfilePresenterImpl", "getMealWithDay:6 "+mealWithDays.getDay());
//                            profileInterface.setDay6(mealWithDays);
//                            break;
//                        case "7":
//                            Log.d("ProfilePresenterImpl", "getMealWithDay:7 "+mealWithDays.getDay());
//                            profileInterface.setDay7(mealWithDays);
//                            break;
//                    }
//                    }, e -> {profileInterface.showErrorMsg(e.getMessage());}));
//    }


    }

    @Override
    public void onSuccess() {
        profileInterface.showErrorMsg("signed out successfully");
        profileInterface.finish();

    }

    @Override
    public void onFailure(String errorMsg) {
        profileInterface.showErrorMsg(errorMsg);
    }
}
