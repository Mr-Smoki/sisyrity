package com.example.usersapp.presentation.activities.edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.sisyrity.databinding.ActivityAddEditBinding;
import com.example.usersapp.repository.Data;
import com.example.usersapp.repository.database.entity.Service;

public class AddEditActivity extends AppCompatActivity {

    ActivityAddEditBinding binding;
    Data data;
    Service localService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.urlPhotoET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                data.loadImage(binding.urlPhotoET.getText().toString(),binding.photoView);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.editOrAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(localService!=null){
                    localService.title = binding.titleET.getText().toString();
                    localService.short_description=binding.discriptionET.getText().toString();
                    localService.full_description=binding.fulldiscriptionET.getText().toString();
                    localService.min_price=Double.parseDouble(binding.minPriceET.getText().toString());
                    localService.max_price=Double.parseDouble(binding.maxPriceET.getText().toString());
                    localService.uRLPhoto=binding.urlPhotoET.getText().toString();
                    data.data.serviceDao().insert(localService);
                    finish();
                }
            }
        });

        data = Data.getInstance(getApplicationContext());
        int id = getIntent().getIntExtra(Data.ID,-1);
        if(id==-1) {
            localService = new Service();
            data.loadImage("",binding.photoView);
        }
        else{
            binding.deleteButton.setVisibility(View.VISIBLE);

            binding.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDeleteMessage();
                }
            });

            binding.editOrAddButton.setText("Редактировать");
            data.data.serviceDao().getServiceInId(id).observe(this, new Observer<Service>() {
                @Override
                public void onChanged(Service service) {
                    if(service==null)
                        finish();
                    localService=service;
                    binding.discriptionET.setText(service.short_description);
                    binding.fulldiscriptionET.setText(service.full_description);
                    binding.titleET.setText(service.title);
                    binding.urlPhotoET.setText(service.uRLPhoto);
                    binding.minPriceET.setText(String.valueOf(service.min_price));
                    binding.maxPriceET.setText(String.valueOf(service.max_price));
                }
            });


        }


    }

    private void showDeleteMessage() {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setMessage("Вы уверены?")
        .setTitle("Удаление услуги")
                .setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.data.serviceDao().delete(localService);
                        finish();
                    }
                }).setNegativeButton("Отмена",null);
        builder.show();
    }
}