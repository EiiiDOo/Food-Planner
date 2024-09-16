package com.example.foodplanner.FireBase;


import android.util.Log;


import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealWithDay;
import com.example.foodplanner.Model.MealsResponse;
import com.example.foodplanner.Model.MealsWithDayResponse;
import com.example.foodplanner.Profile.Presenter.ProfilePresenter;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FireBaseRemoteDatasourceImpl implements FireBaseRemoteDatasource {
    public static final String USER_KEY = "Users";
    private static final String FAVOURITE_MEALS_KEY = "favouriteMeals";
    private static final String PLAN_MEALS_KEY = "planMeals";

    private static final String TAG = "FireBaseRemoteDatasourceImpl";
    private static FireBaseRemoteDatasourceImpl instance = null;
    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore firestore;

    private FireBaseRemoteDatasourceImpl() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.getCurrentUser();

        firestore = FirebaseFirestore.getInstance();
    }

    public static FireBaseRemoteDatasourceImpl getInstance() {
        if (instance == null) {
            instance = new FireBaseRemoteDatasourceImpl();
        }
        return instance;
    }

    @Override
    public void signUp(String email, String password, FireBaseCallback fireBaseCallback) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        fireBaseCallback.
                                onSuccess("Sign Up Successfully");
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        fireBaseCallback.onFailure(Objects.requireNonNull(task.getException()).getMessage());
                    }
                });
    }

    @Override
    public void signOut() {
        firebaseAuth.signOut();
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

    @Override
    public void signin(String email, String password, FireBaseCallback fireBaseCallback) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        fireBaseCallback.onSuccess("Signin Successfully");
                    } else {
                        // If sign in fails, display a message to the user.
                        fireBaseCallback.onFailure(Objects.requireNonNull(task.getException()).getMessage());
                    }
                });
    }

    @Override
    public void signInUsingGmailAccount(String idToken, FireBaseCallback fireBaseCallback) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                fireBaseCallback.onSuccess("Signin With Gmail Successfully");
            } else {
                fireBaseCallback.onFailure(Objects.requireNonNull(task.getException()).getMessage());
            }
        });
    }

    @Override
    public void backupFavouriteMeals(ArrayList<Meal> favouriteMeals, ArrayList<MealWithDay> planMeal, FireBaseCallback fireBaseCallback) {
        Map<String, String> favMealsMap = new HashMap<>();
        Map<String, String> planMealsMap = new HashMap<>();
        favMealsMap.put("TEST", "{\"meals\":" + favouriteMeals.toString() + "}");
        planMealsMap.put("TEST", "{\"mealsWithDays\":" + planMeal.toString() + "}");
        firestore.collection(USER_KEY).document(getCurrentUser().getUid())
                .collection(FAVOURITE_MEALS_KEY)
                .document(FAVOURITE_MEALS_KEY + "Collection")
                .set(favMealsMap).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        fireBaseCallback.onSuccess("Backup Favourite Meal SuccessB");
                    } else
                        fireBaseCallback.onFailure(Objects.requireNonNull(task.getException()).getMessage());
                });
        firestore.collection(USER_KEY).document(getCurrentUser().getUid())
                .collection(PLAN_MEALS_KEY)
                .document(PLAN_MEALS_KEY + "Collection")
                .set(planMealsMap).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        fireBaseCallback.onSuccess("Backup Plan Meal SuccessB");
                    } else
                        fireBaseCallback.onFailure(Objects.requireNonNull(task.getException()).getMessage());
                });

    }

    @Override
    public void downloadFavouriteMeals(FireBaseCallback fireBaseCallback, ProfilePresenter profilePresenter) {

        firestore.collection(USER_KEY).document(getCurrentUser().getUid())
                .collection(FAVOURITE_MEALS_KEY)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Map<String, Object> singleMealMap = new HashMap<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            singleMealMap = document.getData();
                        }
                        assert singleMealMap != null;
                        profilePresenter.restoreAllFavouriteMeals(new Gson().fromJson((String) Objects.requireNonNull(singleMealMap.get("TEST")), MealsResponse.class).getMeals());

                        fireBaseCallback.onSuccess("Downloading. . . .");
                    } else
                        fireBaseCallback.onFailure(Objects.requireNonNull(task.getException()).getMessage());

                });
    }

    @Override
    public void downloadPlanMeals(FireBaseCallback fireBaseCallback, ProfilePresenter profilePresenter) {

        firestore.collection(USER_KEY).document(getCurrentUser().getUid())
                .collection(PLAN_MEALS_KEY)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Map<String, Object> singleMealMap = new HashMap<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            singleMealMap = document.getData();
                        }
                        assert singleMealMap != null;
                        profilePresenter.restoreAllPlanMeals(new Gson().fromJson((String) Objects.requireNonNull(singleMealMap.get("TEST")), MealsWithDayResponse.class).getMeals());

                        fireBaseCallback.onSuccess("Downloading. . . .");
                    } else
                        fireBaseCallback.onFailure(Objects.requireNonNull(task.getException()).getMessage());

                });
    }

}
