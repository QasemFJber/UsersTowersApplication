package com.example.userstowersapplication.controller;

import android.util.Log;

import com.example.userstowersapplication.interfaces.ContentCallBack;
import com.example.userstowersapplication.interfaces.ShowListenirs;
import com.example.userstowersapplication.modils.Advertisements;
import com.example.userstowersapplication.modils.BaseResponse;
import com.example.userstowersapplication.modils.Categorie;
import com.example.userstowersapplication.modils.Operations;
import com.example.userstowersapplication.modils.ShowCategorie;
import com.example.userstowersapplication.towers.towrescontroller.ApiController;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesController {

    public void getAllCategories(ContentCallBack<Categorie> callBack){
        Call<BaseResponse<Categorie>> call = ApiController.getInstance().getRetrofitRequests().getAllCategories();
        call.enqueue(new Callback<BaseResponse<Categorie>>() {
            @Override
            public void onResponse(Call<BaseResponse<Categorie>> call, Response<BaseResponse<Categorie>> response) {
                if (response.isSuccessful() && response.body() != null){
                    callBack.onSuccess(response.body().list);
                }else {
                    try {
                        String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(error);
                        callBack.onFailure(jsonObject.getString("message"));
                        Log.d("API REQUEST","REQUEST"+jsonObject.getString("message"));

                    }catch (JSONException | IOException jsonException){

                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Categorie>> call, Throwable t) {

            }
        });

    }

    public void getAllCategoriesOfCategorieId(int id,ShowListenirs callBack){
        Call<BaseResponse<ShowCategorie>> getAll = ApiController.getInstance().getRetrofitRequests().getAllCategoriesOfCategorieId(id);
        getAll.enqueue(new Callback<BaseResponse<ShowCategorie>>() {
            @Override
            public void onResponse(Call<BaseResponse<ShowCategorie>> call, Response<BaseResponse<ShowCategorie>> response) {
                if (response.isSuccessful() && response.body() != null){
                    callBack.onSuccess(response.body().list);
                }else {
                    try {
                        String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(error);
                        callBack.onFailure(jsonObject.getString("message"));
                        Log.d("API REQUEST","REQUEST"+jsonObject.getString("message"));
                    }catch (JSONException | IOException jsonException){

                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ShowCategorie>> call, Throwable t) {

            }
        });

    }
    public void getAllAdvertisements(ContentCallBack<Advertisements> callBack){
        Call<BaseResponse<Advertisements>> getAllAdvertisements = ApiController.getInstance().getRetrofitRequests().getAllAdvertisements();
        getAllAdvertisements.enqueue(new Callback<BaseResponse<Advertisements>>() {
            @Override
            public void onResponse(Call<BaseResponse<Advertisements>> call, Response<BaseResponse<Advertisements>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body().list);
                } else {
                    try {
                        String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(error);
                        callBack.onFailure(jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Advertisements>> call, Throwable t) {
                callBack.onFailure("");

            }
        });

    }
    public void getAllOperations(ContentCallBack<Operations> callBack){
        Call<BaseResponse<Operations>> getAllOperations = ApiController.getInstance().getRetrofitRequests().getAllOperations();
        getAllOperations.enqueue(new Callback<BaseResponse<Operations>>() {
            @Override
            public void onResponse(Call<BaseResponse<Operations>> call, Response<BaseResponse<Operations>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body().list);
                } else {
                    try {
                        String error = new String(response.errorBody().bytes(), StandardCharsets.UTF_8);
                        JSONObject jsonObject = new JSONObject(error);
                        callBack.onFailure(jsonObject.getString("message"));
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Operations>> call, Throwable t) {
                callBack.onFailure("");

            }
        });

    }
}
