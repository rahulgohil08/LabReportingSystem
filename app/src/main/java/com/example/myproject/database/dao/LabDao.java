package com.example.myproject.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myproject.database.entities.Lab;
import com.example.myproject.database.entities.User;

import java.util.List;

@Dao
public interface LabDao {

    @Query("SELECT * FROM lab")
    List<Lab> getAll();

    @Query("SELECT count(labId) FROM lab")
    int getLabCount();

    @Query("SELECT * FROM lab WHERE labId = :labId")
    Lab getLabById(int labId);

    @Query("SELECT * FROM lab WHERE email = :email and password = :password")
    Lab loginLab(String email, String password);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertLab(Lab lab);

    @Update
    void updateLab(Lab lab);

    @Query("DELETE FROM lab WHERE labId = :labId")
    int deleteLab(int labId);
}