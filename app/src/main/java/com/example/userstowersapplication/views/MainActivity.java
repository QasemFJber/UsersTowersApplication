package com.example.userstowersapplication.views;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.userstowersapplication.Broadcastreciver.NetworkChangeListiners;
import com.example.userstowersapplication.Dialog.CustomDialog;
import com.example.userstowersapplication.R;
import com.example.userstowersapplication.adapters.OperationsAdapter;
import com.example.userstowersapplication.controller.AuthController;
import com.example.userstowersapplication.controller.CategoriesController;
import com.example.userstowersapplication.databinding.ActivityMainBinding;
import com.example.userstowersapplication.interfaces.AuthCallBack;
import com.example.userstowersapplication.interfaces.ClickItem;
import com.example.userstowersapplication.interfaces.ContentCallBack;
import com.example.userstowersapplication.modils.Categorie;
import com.example.userstowersapplication.modils.Operations;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements ClickItem {
    private ActivityMainBinding binding;
    AuthController authController = new AuthController();
    CategoriesController categoriesController = new CategoriesController();
    private List<Categorie> categories = new ArrayList<>();
    NetworkChangeListiners networkChangeListiners = new NetworkChangeListiners();
    private CustomDialog dialog = new CustomDialog(this);
    OperationsAdapter adapter = new OperationsAdapter(new ArrayList<>(),this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeView();
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllOperations();
                binding.swipeRefresh.setRefreshing(false);
            }
        });
    }

    private void operationsSccren() {
        setTitle("HOME");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yl)));
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.black));
    }

    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListiners,intentFilter);
        super.onStart();


    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListiners);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.residentmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.advertisements) {
            Intent intent = new Intent(getApplicationContext(), Advertisements.class);
            startActivity(intent);
        }else if (item.getItemId() == R.id.change_password){
            Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
            startActivity(intent);
        }else if (item.getItemId() == R.id.logout){
           logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeView() {
        getAllOperations();
        operationsSccren();
        loadDialog();
    }

    private void loadDialog() {
        dialog.startLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismissDialog();
            }
        },3000);
    }


    private void getAllOperations() {
        categoriesController.getAllOperations(new ContentCallBack<Operations>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(List<Operations> list) {
                adapter.setOperationsList(list);
                adapter.notifyDataSetChanged();
                binding.rvCategories.setAdapter(adapter);
                binding.rvCategories.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.rvCategories.setHasFixedSize(true);
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(binding.getRoot(),message, Snackbar.LENGTH_LONG).show();

            }
        });
    }

    private void logout() {
        authController.logout(new AuthCallBack() {
            @Override
            public void onSuccess(String message) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(String message) {
                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public void onClick(Operations operations) {
        Toast.makeText(this, "The id is :"+operations.amount, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(getApplicationContext(),Details.class);
//        intent.putExtra("id",2);
//        intent.putExtra("categoryName",operations.categoryName);
//        intent.putExtra("amount",String.valueOf(operations.amount));
//        intent.putExtra("name",operations.employee.name);
//        intent.putExtra("date",operations.date);
//        intent.putExtra("details",operations.details);
//        intent.putExtra("towerName",operations.employee.towerName);
//        intent.putExtra("imageUrl",operations.employee.imageUrl);
//        startActivity(intent);

    }
}