package com.example.userstowersapplication.views;

import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.userstowersapplication.Broadcastreciver.NetworkChangeListiners;
import com.example.userstowersapplication.Dialog.CustomDialog;
import com.example.userstowersapplication.R;
import com.example.userstowersapplication.adapters.ShowAdapter;
import com.example.userstowersapplication.controller.CategoriesController;
import com.example.userstowersapplication.databinding.ActivityShowCategorieBinding;
import com.example.userstowersapplication.interfaces.ShowListenirs;
import com.example.userstowersapplication.modils.ShowCategorie;

import java.util.ArrayList;
import java.util.List;

public class ShowCategorieOFId extends AppCompatActivity {
    private ActivityShowCategorieBinding binding;
    CategoriesController categoriesController = new CategoriesController();
    private List<ShowCategorie> categories = new ArrayList<>();
    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();
    private CustomDialog dialog = new CustomDialog(this);
    private ShowAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowCategorieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeView();
        dialog.startLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismissDialog();
            }
        }, 3000);
    }

    private void initializeView() {
        getCategoriesOfId();
        operationsSccren();
    }

    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListiners, intentFilter);
        super.onStart();


    }



    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListiners);
        super.onStop();
    }


    private void getCategoriesOfId() {
        categoriesController.getAllCategoriesOfCategorieId(1, new ShowListenirs() {
            @Override
            public void onSuccess(List<ShowCategorie> list) {
                adapter.setCategorieList(list);
                binding.rvCateg.setAdapter(adapter);
                binding.rvCateg.setLayoutManager(new LinearLayoutManager(ShowCategorieOFId.this));
                binding.rvCateg.setHasFixedSize(true);
            }

            @Override
            public void onFailure(String message) {

            }
        });


    }

    private void operationsSccren () {
        setTitle("OperationsOfIdCategories");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yl)));
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowCategorieOFId.this, R.color.black));
    }
}