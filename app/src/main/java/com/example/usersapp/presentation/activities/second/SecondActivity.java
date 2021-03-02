package com.example.usersapp.presentation.activities.second;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sisyrity.databinding.ActivitySecondBinding;
import com.example.usersapp.presentation.activities.edit.AddEditActivity;
import com.example.usersapp.repository.Data;
import com.example.usersapp.repository.database.entity.Service;

public class SecondActivity extends AppCompatActivity {
    LayoutInflater inflater;
    ActivitySecondBinding binding;
    int id = 0;
    Service localService;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater=getLayoutInflater();
        binding = ActivitySecondBinding.inflate (inflater);
        setContentView(binding.getRoot());

        binding.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,AddEditActivity.class);
                intent.putExtra(Data.ID,id);
                startActivity(intent);
            }
        });

        if(data.admin)
        {
            binding.editButton.setVisibility(View.VISIBLE);
        }

        localService =new Service();
        data=Data.getInstance(this);
        id = getIntent().getIntExtra("ID",0);
        data.data.serviceDao().getServiceInId(id).observe(SecondActivity.this, new Observer<Service> () {
            @Override
            public void onChanged(Service services) {
                localService = services;
                binding.TitleView.setText(localService.title);
                binding.infoView.setText(localService.full_description);
                binding.priceView.setText(localService.range_price());
                data.loadImage(localService.uRLPhoto,binding.photoView);
            }
        });
    }
}
