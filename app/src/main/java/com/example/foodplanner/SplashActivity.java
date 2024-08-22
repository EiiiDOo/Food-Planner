package com.example.foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

public class SplashActivity extends AppCompatActivity {
    static long SPLASH_DISPLAY_LENGTH = 100;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        progressBar = findViewById(R.id.progressBar);
        new Handler(Looper.getMainLooper()).postDelayed(() -> progressBar.setVisibility(View.VISIBLE), 1000);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, AuthorizActivity.class));
            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}