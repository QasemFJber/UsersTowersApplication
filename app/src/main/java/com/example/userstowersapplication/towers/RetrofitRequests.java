package com.example.userstowersapplication.towers;

import com.example.userstowersapplication.modils.Advertisements;
import com.example.userstowersapplication.modils.BaseResponse;
import com.example.userstowersapplication.modils.Categorie;
import com.example.userstowersapplication.modils.Operations;
import com.example.userstowersapplication.modils.Resident;
import com.example.userstowersapplication.modils.ShowCategorie;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

//Service Interface - Retrofit - API EndPoints
public interface RetrofitRequests {



    @FormUrlEncoded
    @POST("auth/login")
    Call<BaseResponse<Resident>> login (@Field("email") String email , @Field("password") String password );

    @GET("auth/logout")
    Call<BaseResponse> logout();

    @FormUrlEncoded
    @POST("auth/change-password")
    Call<BaseResponse> changePassword(@Field("current_password") String current_password,@Field("new_password") String new_password,@Field("new_password_confirmation") String new_password_confirmation);

    @GET("categories")
    Call<BaseResponse<Categorie>> getAllCategories();


    @GET("categories/{id}")
    Call<BaseResponse<ShowCategorie>> getAllCategoriesOfCategorieId(@Path("id") int id);


    @GET("advertisements")
    Call<BaseResponse<Advertisements>> getAllAdvertisements();


    @GET("operations")
    Call<BaseResponse<Operations>> getAllOperations();


}
