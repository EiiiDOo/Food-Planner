package com.example.foodplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    NavController navController;
    SharedPreferences sp;
    public static String statUser;
    public static List<Meal> list ;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        statUser = intent.getStringExtra("user");
        sp = getSharedPreferences("userdetails", MODE_PRIVATE);
        statUser = sp.getString("user", "guest");
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.gary));
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.gary));

        bottomNavigationView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        if (!(statUser.equals("guest"))) {
            ReposateryImpl reposatery = ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this));
            compositeDisposable.add(reposatery.getFavMeals(statUser).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(meals -> list = meals, e -> e.printStackTrace()));
        }
//        compositeDisposable.add(NetworkUtil.observeNetworkConnectivity(this).subscribe(e -> {
//            if (e) {
//                Toast.makeText(this, "YOUSRY TRUE", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "YOUSRY FALSE", Toast.LENGTH_SHORT).show();
//                NoInternetDialog d = new NoInternetDialog();
//                        d.show(getSupportFragmentManager(), null);
//            }
//    }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}