package com.example.usersapp.presentation.activities.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import com.example.sisyrity.R;
import com.example.sisyrity.databinding.ActivityLoginBinding;
import com.example.usersapp.presentation.activities.first.FirstActivity;
import com.example.usersapp.presentation.activities.second.SecondActivity;
import com.example.usersapp.repository.Data;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.userTypePicker.setMaxValue(1);
        binding.userTypePicker.setMinValue(0);
        binding.userTypePicker.setDisplayedValues(new String[]{"Администратор", "Клиент"});
        binding.signInButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //сохранение куда-то
                boolean admin = binding.userTypePicker.getValue() == 0;
                //переход дальше
                Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
                Data.getInstance(getApplicationContext()).admin = binding.userTypePicker.getValue()==0;
                startActivity(intent);
            }
        });
    }
}