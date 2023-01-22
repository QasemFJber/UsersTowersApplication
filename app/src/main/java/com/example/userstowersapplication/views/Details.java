package com.example.userstowersapplication.views;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.userstowersapplication.Broadcastreciver.NetworkChangeListiners;
import com.example.userstowersapplication.R;
import com.example.userstowersapplication.databinding.ActivityDetailsBinding;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    ActivityDetailsBinding binding;
    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeView();


    }


    private void initializeView() {
        operationsSccren();
        setDataInTextViews();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        binding.imageView4.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.tvBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }


    @Override
    protected void onStart() {

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListiners, intentFilter);
        super.onStart();


    }

    private void setDataInTextViews(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        if (id == 1){
            Picasso.get().load(intent.getStringExtra("imageUrl")).into(binding.imageView2);
            binding.tvCategoryNameDetails.setText("TITLE");
            binding.tvAmountDetails.setText("INFO");
            binding.tvResidentNameDetails.setText("TOWERS NAME");
            binding.etCategoryNameDetails.setText(intent.getStringExtra("title"));
            binding.etAmountDetails.setText(intent.getStringExtra("info"));
            binding.etResidentNameDetails.setText(intent.getStringExtra("towerName"));
            binding.tvDateDetails.setVisibility(View.INVISIBLE);
            binding.etDateDetails.setVisibility(View.INVISIBLE);
            binding.tvDetailsDeta.setVisibility(View.INVISIBLE);
            binding.etDetailsDeta.setVisibility(View.INVISIBLE);
            binding.tvTowresNameDetails.setVisibility(View.INVISIBLE);
            binding.etTowresNameDetails.setVisibility(View.INVISIBLE);
            binding.tvEmailDetails.setVisibility(View.INVISIBLE);
            binding.etEmailDetails.setVisibility(View.INVISIBLE);
        }else if (id == 2){

        }



    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListiners);
        super.onStop();
    }

    private void operationsSccren () {
        setTitle("DETAILS");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yl)));
        getWindow().setStatusBarColor(ContextCompat.getColor(Details.this, R.color.black));
    }
}