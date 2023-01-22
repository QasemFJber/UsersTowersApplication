package com.example.userstowersapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userstowersapplication.databinding.ItemOperationsBinding;
import com.example.userstowersapplication.interfaces.ClickItem;
import com.example.userstowersapplication.modils.Operations;

import java.util.List;

public class OperationsAdapter extends RecyclerView.Adapter<OperationsAdapter.OperationsViewHolder> {
    private List<Operations> operationsList ;
    private ClickItem clickItem;

    public OperationsAdapter(List<Operations> operationsList,ClickItem clickItem) {
        this.operationsList = operationsList;
        this.clickItem = clickItem;
    }

    public void setOperationsList(List<Operations> operationsList) {
        this.operationsList = operationsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OperationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOperationsBinding binding = ItemOperationsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OperationsViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull OperationsViewHolder holder, int position) {
        Operations operations = operationsList.get(position);
        holder.binding.tvAmount.setText(operations.amount);
        holder.binding.tvItemDate.setText(operations.date);
        holder.binding.tvItemDetails.setText(operations.details);
        holder.binding.tvItemname.setText(operations.categoryName);
        holder.binding.tvId.setText(String.valueOf(operations.id));

//        Picasso.get().load(operations.employee.imageUrl).into(holder.binding.imag);

        holder.binding.card.setOnClickListener(v -> {
            clickItem.onClick(operations);
        });

    }

    @Override
    public int getItemCount() {
        return operationsList.size();
    }

    class OperationsViewHolder extends RecyclerView.ViewHolder{
        ItemOperationsBinding binding;

        public OperationsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemOperationsBinding.bind(itemView);
        }
    }
}

