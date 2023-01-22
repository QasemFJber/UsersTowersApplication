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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.userstowersapplication.Broadcastreciver.NetworkChangeListiners;
import com.example.userstowersapplication.Dialog.CustomDialog;
import com.example.userstowersapplication.R;
import com.example.userstowersapplication.adapters.AdvertisementsAdapter;
import com.example.userstowersapplication.controller.CategoriesController;
import com.example.userstowersapplication.databinding.ActivityAdvertisementsBinding;
import com.example.userstowersapplication.interfaces.ClickItemRecycler;
import com.example.userstowersapplication.interfaces.ContentCallBack;

import java.util.ArrayList;
import java.util.List;


public class Advertisements extends AppCompatActivity implements ClickItemRecycler {
    ActivityAdvertisementsBinding binding;
    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();
    private AdvertisementsAdapter adapter = new AdvertisementsAdapter(new ArrayList<>(),this) ;
    private CustomDialog dialog = new CustomDialog(this);
    CategoriesController categoriesController = new CategoriesController();
    private List<com.example.userstowersapplication.modils.Advertisements> advertisements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdvertisementsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeView();
    }


    private void initializeView(){
        operationsSccren();
        getAllAdvertisements();
        dialogLoad();


    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(networkChangeListiners);
    }

    private void dialogLoad() {
        dialog.startLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismissDialog();
            }
        },3000);
    }

    private void operationsSccren() {
        setTitle("Advertisements");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yl)));
        getWindow().setStatusBarColor(ContextCompat.getColor(Advertisements.this, R.color.black));

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListiners,intentFilter);
    }
    private void getAllAdvertisements (){
        categoriesController.getAllAdvertisements(new ContentCallBack<com.example.userstowersapplication.modils.Advertisements>() {
            @Override
            public void onSuccess(List<com.example.userstowersapplication.modils.Advertisements> list) {
                advertisements = list;
                if (list.size() ==0){
                    dialog.dismissDialog();
                    binding.tvData.setVisibility(View.VISIBLE);
                    binding.nodata.setVisibility(View.VISIBLE);
                }
                adapter.setAdvertisementsList(list);
                binding.rvAdvertisements.setAdapter(adapter);
                binding.rvAdvertisements.setHasFixedSize(true);
                binding.rvAdvertisements.setLayoutManager(new LinearLayoutManager(Advertisements.this));
            }

            @Override
            public void onFailure(String message) {

            }
        });
    }


    @Override
    public void onClick(com.example.userstowersapplication.modils.Advertisements advertisements) {
        Intent intent = new Intent(getApplicationContext(),Details.class);
        intent.putExtra("title",advertisements.title);
        intent.putExtra("info",advertisements.info);
        intent.putExtra("towerName",advertisements.towerName);
        intent.putExtra("imageUrl",advertisements.imageUrl);
        intent.putExtra("id",1);
        startActivity(intent);

    }
}