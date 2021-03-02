package com.example.usersapp.repository;

import android.content.Context;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import com.example.sisyrity.R;
import com.example.usersapp.repository.database.AppData;
import com.example.usersapp.repository.database.entity.Service;

import java.util.List;

public class Data {

    public static final String ID = "id";

    private static Data instance;
    public boolean admin;
    public AppData data;
    public RequestManager glide;


    private Data(Context context)
    {
        data = Room.databaseBuilder(context, AppData.class, "ServiceDB").allowMainThreadQueries().build();
        glide = Glide.with(context);

    }
    public static Data getInstance(Context context)
    {
        if (instance==null)
            instance=new Data(context);
        return instance;
    }
    public LiveData <List<Service>> GetAllService ()
    {
        return data.serviceDao().getAllService();
    }
    public LiveData <Service> GetServiceID (int id)
    {
        return data.serviceDao().getServiceInId(id);
    }


    public void loadImage(String url, ImageView imageView){
        glide.load(url)
                .placeholder(R.drawable.photo1)
                .into(imageView);
    }
}
