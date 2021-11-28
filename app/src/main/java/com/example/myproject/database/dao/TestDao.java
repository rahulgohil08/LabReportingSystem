package com.example.myproject.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myproject.database.entities.Lab;
import com.example.myproject.database.entities.Test;

import java.util.List;

@Dao
public interface TestDao {

    @Query("SELECT * FROM test")
    List<Test> getAll();

    @Query("SELECT count(testId) FROM test")
    int getTestCount();

    @Query("SELECT * FROM test WHERE testId = :testId")
    Test getTestById(int testId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertTest(Test test);

    @Update
    void updateTest(Test test);

    @Query("DELETE FROM test WHERE testId = :testId")
    int deleteTest(int testId);
}