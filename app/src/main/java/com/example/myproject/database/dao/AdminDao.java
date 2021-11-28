package com.example.myproject.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myproject.database.entities.Admin;

import java.util.List;

@Dao
public interface AdminDao {

    @Query("SELECT * FROM admin")
    List<Admin> getAll();

    @Query("SELECT * FROM admin WHERE email = :email and password = :password")
    Admin loginAdmin(String email, String password);



//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAdmin(Admin admin);

    @Delete
    void delete(Admin admin);

    @Query("DELETE FROM admin")
    void deleteAll();
}