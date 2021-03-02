package com.example.usersapp.repository.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Service {
    @PrimaryKey(autoGenerate = true)
    public int ServiceID;

    public String uRLPhoto;
    public String title;
    public String short_description;
    public String full_description;
    public double min_price;
    public double max_price;
    public String range_price (){
        return "От "+min_price + " до " + max_price;
    }

}
