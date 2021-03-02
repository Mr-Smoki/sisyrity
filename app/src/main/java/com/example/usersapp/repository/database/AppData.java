package com.example.usersapp.repository.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.usersapp.repository.database.dao.ServiceDao;
import com.example.usersapp.repository.database.entity.Service;

@Database(entities = {Service.class},version = 1)
public abstract class AppData extends RoomDatabase {
    public abstract ServiceDao serviceDao();

}
