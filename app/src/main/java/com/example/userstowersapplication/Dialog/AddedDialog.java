package com.example.userstowersapplication.Dialog;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.example.userstowersapplication.R;


public class AddedDialog {
    private AlertDialog dialog;
    private Activity activity;

    public AddedDialog(Activity activity1){
        activity =activity1;
    }

    public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        builder.setView(layoutInflater.inflate(R.layout.updatedcustom,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }


    public void dismissDialog(){
        dialog.dismiss();
    }
}
