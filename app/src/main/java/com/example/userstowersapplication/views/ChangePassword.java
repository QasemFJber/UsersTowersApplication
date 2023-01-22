package com.example.userstowersapplication.views;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.userstowersapplication.Broadcastreciver.NetworkChangeListiners;
import com.example.userstowersapplication.Dialog.AddedDialog;
import com.example.userstowersapplication.R;
import com.example.userstowersapplication.controller.AuthController;
import com.example.userstowersapplication.databinding.ActivityForgotPasswordBinding;
import com.example.userstowersapplication.interfaces.AuthCallBack;
import com.google.android.material.snackbar.Snackbar;

public class ChangePassword extends AppCompatActivity  implements View.OnClickListener {
    private ActivityForgotPasswordBinding binding;
    AuthController authController = new AuthController();
    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();
    AddedDialog addedDialog = new AddedDialog(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Change Password");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yl)));
        getWindow().setStatusBarColor(ContextCompat.getColor(ChangePassword.this,R.color.black));
        setOnClick();

    }

    private void setOnClick() {
        binding.btnSave.setOnClickListener(this::onClick);
        binding.tvBack.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save){
            changePassword();
        } else if (v.getId() == R.id.tv_back) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void changePassword(){
        authController.changePassword(binding.etCurrentPassword.getText().toString().trim(), binding.etNewPassword.getText().toString().trim(), binding.etNewPasswordConfirmation.getText().toString().trim(), new AuthCallBack() {
            @Override
            public void onSuccess(String message) {
                addedDialog.startLoading();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addedDialog.dismissDialog();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                },3500);

            }

            @Override
            public void onFailure(String message) {

                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeListiners);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListiners,intentFilter);
    }
}