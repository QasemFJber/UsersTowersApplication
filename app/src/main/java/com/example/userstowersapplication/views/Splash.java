package com.example.userstowersapplication.views;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.userstowersapplication.Broadcastreciver.NetworkChangeListiners;
import com.example.userstowersapplication.databinding.ActivitySplashBinding;
import com.example.userstowersapplication.prefs.AppSharedPreferences;


public class Splash extends AppCompatActivity {
    ActivitySplashBinding binding;
    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        YoYo.with(Techniques.SlideInUp.getAnimator()).repeat(0).duration(4400).playOn(binding.textView);

    }

    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListiners,intentFilter);
        super.onStart();
        controlSplashActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeListiners);
        finish();
    }

    private void controlSplashActivity() {
        //3000ms - 3s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isLoggedIn = AppSharedPreferences.getInstance().isLoggedIn();
                Intent intent = new Intent(getApplicationContext(), isLoggedIn ? MainActivity.class : Login.class);
                startActivity(intent);
            }
        }, 1000);
    }
}