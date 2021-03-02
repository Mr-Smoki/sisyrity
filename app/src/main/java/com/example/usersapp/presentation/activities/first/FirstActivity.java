package com.example.usersapp.presentation.activities.first;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usersapp.presentation.activities.edit.AddEditActivity;
import com.example.sisyrity.databinding.ActivityFirstBinding;
import com.example.sisyrity.databinding.ItemPriseBinding;
import com.example.usersapp.presentation.activities.second.SecondActivity;
import com.example.usersapp.repository.Data;
import com.example.usersapp.repository.database.entity.Service;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    LayoutInflater inflater;
    ActivityFirstBinding binding;
    ServiceAdapter adapter;
    List<Service> localServices=new ArrayList<>();;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstBinding.inflate (getLayoutInflater());
        setContentView(binding.getRoot());

        inflater=getLayoutInflater();

        data = Data.getInstance(getApplicationContext());
        adapter=new ServiceAdapter();
        binding.prisesView.setAdapter(adapter);


        data.data.serviceDao().getAllService().observe(this, new Observer<List<Service>>() {
            @Override
            public void onChanged(List<Service> services) {
                FirstActivity.this.localServices = services;
                adapter.notifyDataSetChanged();
            }
        });

        if(data.admin)
        {
            binding.goAddButton.setVisibility(View.VISIBLE);
        }

        binding.goAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this, AddEditActivity.class);
                startActivity(intent);
            }
        });
    }

    private class ServiceAdapter extends RecyclerView.Adapter <ServiceAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(ItemPriseBinding.inflate(inflater,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Service itemService = localServices.get(position);
        holder.itemPriseBinding.nameView.setText(itemService.title);
        holder.itemPriseBinding.descriptionView.setText(itemService.short_description);
        holder.itemPriseBinding.priseView.setText(itemService.range_price());
        data.loadImage(itemService.uRLPhoto,holder.itemPriseBinding.photoView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("ID",itemService.ServiceID);
                startActivity(intent);
            }
        });
        }

        @Override
        public int getItemCount() {
            return localServices.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ItemPriseBinding itemPriseBinding;
            public ViewHolder(@NonNull ItemPriseBinding priseBinding) {
                super(priseBinding.getRoot());
                this.itemPriseBinding = priseBinding;

            }
        }
    }
}