package com.example.userstowersapplication.views;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.userstowersapplication.Broadcastreciver.NetworkChangeListiners;
import com.example.userstowersapplication.Dialog.CustomDialog;
import com.example.userstowersapplication.R;
import com.example.userstowersapplication.adapters.ShowAdapter;
import com.example.userstowersapplication.controller.CategoriesController;
import com.example.userstowersapplication.databinding.ActivityShowPayrollBinding;
import com.example.userstowersapplication.interfaces.DetailsClick;
import com.example.userstowersapplication.interfaces.ShowListenirs;
import com.example.userstowersapplication.modils.ShowCategorie;

import java.util.ArrayList;
import java.util.List;

public class ShowPayroll extends AppCompatActivity implements DetailsClick {
    ActivityShowPayrollBinding binding;
    CategoriesController categoriesController = new CategoriesController();
    private List<ShowCategorie> categories = new ArrayList<>();
    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();
    private CustomDialog dialog = new CustomDialog(this);
    private ShowAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowPayrollBinding.inflate(getLayoutInflater());
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
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        Toast.makeText(this, "The id is :"+ id, Toast.LENGTH_SHORT).show();
        categoriesController.getAllCategoriesOfCategorieId(id, new ShowListenirs() {
            @Override
            public void onSuccess(List<ShowCategorie> list) {
                Toast.makeText(ShowPayroll.this, "List size is : "+ list.size(), Toast.LENGTH_SHORT).show();
//                adapter.setCategorieList(list);
//                if (list.size() ==0){
//                    dialog.dismissDialog();
//                    binding.tvData.setVisibility(View.VISIBLE);
//                    binding.nodata.setVisibility(View.VISIBLE);
//                }
//                binding.rvPayroll.setAdapter(adapter);
//                binding.rvPayroll.setLayoutManager(new LinearLayoutManager(ShowPayroll.this));
//                binding.rvPayroll.setHasFixedSize(true);
            }

            @Override
            public void onFailure(String message) {

            }
        });

    }
    private void operationsSccren() {
        setTitle("Payroll");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yl)));
        getWindow().setStatusBarColor(ContextCompat.getColor(ShowPayroll.this,R.color.black));

    }

    @Override
    public void onClick(ShowCategorie showCategorie) {
        Intent intent = new Intent(getApplicationContext(),Details.class);
        intent.putExtra("categoryName",showCategorie.categoryName);
        intent.putExtra("amount",showCategorie.amount);
        intent.putExtra("residentname",showCategorie.resident.name);
        intent.putExtra("date",showCategorie.date);
        intent.putExtra("residentemail",showCategorie.resident.email);
        intent.putExtra("residenttowerName",showCategorie.resident.towerName);
        intent.putExtra("details",showCategorie.details);
        intent.putExtra("image",showCategorie.resident.imageUrl);
        startActivity(intent);
    }
}