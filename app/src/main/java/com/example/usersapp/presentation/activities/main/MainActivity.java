package com.example.usersapp.presentation.activities.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.usersapp.presentation.activities.login.LoginActivity;
import com.example.sisyrity.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Thread.sleep(2000); //Приостанавливает поток на 1 секунду
            Intent i;
            i = new Intent (MainActivity.this, LoginActivity.class);
            startActivity(i);
        } catch (Exception e) {

        }
    }
}