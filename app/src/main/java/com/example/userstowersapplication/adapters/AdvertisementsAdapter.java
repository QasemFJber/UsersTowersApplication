package com.example.userstowersapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userstowersapplication.databinding.ItemAdvertisementsBinding;
import com.example.userstowersapplication.interfaces.ClickItemRecycler;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdvertisementsAdapter extends RecyclerView.Adapter<AdvertisementsAdapter.AdvertisementsViewHolder> {
    private List<com.example.userstowersapplication.modils.Advertisements> advertisementsList;
    private ClickItemRecycler clickItemRecycler;

    public AdvertisementsAdapter(List<com.example.userstowersapplication.modils.Advertisements> advertisementsList,ClickItemRecycler clickItemRecycler) {
        this.advertisementsList = advertisementsList;
        this.clickItemRecycler=clickItemRecycler;
    }

    public void setAdvertisementsList(List<com.example.userstowersapplication.modils.Advertisements> advertisementsList) {
        this.advertisementsList = advertisementsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdvertisementsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdvertisementsBinding binding = ItemAdvertisementsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new AdvertisementsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull AdvertisementsViewHolder holder, int position) {
        com.example.userstowersapplication.modils.Advertisements advertisements = advertisementsList.get(position);
        holder.binding.tvItemTitle.setText(advertisements.title);
        holder.binding.tvItemInfo.setText(advertisements.info);
        Picasso.get().load(advertisements.imageUrl).into(holder.binding.imag);
        holder.binding.card.setOnClickListener(v -> {
            clickItemRecycler.onClick(advertisements);
        });


    }

    @Override
    public int getItemCount() {
        return advertisementsList.size();
    }

    static class AdvertisementsViewHolder extends RecyclerView.ViewHolder{

        ItemAdvertisementsBinding binding;
        public AdvertisementsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemAdvertisementsBinding.bind(itemView);
        }
    }

}
