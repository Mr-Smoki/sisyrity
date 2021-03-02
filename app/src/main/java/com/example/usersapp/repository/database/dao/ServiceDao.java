package com.example.usersapp.repository.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;

import com.example.usersapp.repository.database.entity.Service;

import java.util.List;

@Dao
public interface ServiceDao {

    @Query("Select * from Service") //Вывод вех данных из таблицы Service
    LiveData<List<Service>> getAllService();

    @Query("Select * from Service where ServiceID ==:serviceid ") // Вывод сервиса под определнному айди
    LiveData <Service> getServiceInId(int serviceid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Service  service);

    @Delete
    void delete(Service  service);


}
