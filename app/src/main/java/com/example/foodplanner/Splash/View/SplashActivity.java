package com.example.foodplanner.Splash.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodplanner.Authorization.AuthorizActivity;
import com.example.foodplanner.FireBase.FireBaseRemoteDatasourceImpl;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.Model.RepoRoom.Room.MealsfavLocalDataSourceImpl;
import com.example.foodplanner.Model.Reposatery.ReposateryImpl;
import com.example.foodplanner.Network.Base.RemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Splash.Presenter.SplashPresenterImpl;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity implements SplashInterface {
    static long SPLASH_DISPLAY_LENGTH = 1000;
    SplashPresenterImpl splashPresenter;
    ProgressBar progressBar;
    SharedPreferences sp ;
    SharedPreferences.Editor editor ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_splash);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash), (view, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        Window window = getWindow();
        window.setNavigationBarColor(ContextCompat.getColor(this, R.color.gary));
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.gary));

        sp = getSharedPreferences("userdetails", MODE_PRIVATE);
        editor = sp.edit();
        splashPresenter = new SplashPresenterImpl(ReposateryImpl.getInstance(RemoteDataSourceImpl.getInstance(), FireBaseRemoteDatasourceImpl.getInstance(), MealsfavLocalDataSourceImpl.getInstance(this)), this);
        editor.putString("user", splashPresenter.getUserId());
        editor.commit();

        progressBar = findViewById(R.id.progressBar);
        new Handler(Looper.getMainLooper()).postDelayed(() -> progressBar.setVisibility(View.VISIBLE), 1000);
        if (splashPresenter.getUserId() != null) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } else {
            new Thread(() -> {
                try {
                    Thread.sleep(SPLASH_DISPLAY_LENGTH);
                    startActivity(new Intent(SplashActivity.this, AuthorizActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }

    }
}