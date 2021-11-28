package com.example.myproject.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myproject.database.entities.Admin;
import com.example.myproject.database.entities.Test;
import com.example.myproject.database.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT count(userId) FROM user")
    int getUserCount();

    @Query("SELECT * FROM user WHERE email = :email and password = :password")
    User loginUser(String email, String password);

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertUser(User user);


    @Query("DELETE FROM user WHERE userId = :userId")
    int deleteUser(int userId);

    @Query("SELECT count(appointmentId) FROM appointment where user_id =:userId and status = 0")
    int totalPendingAppointment(int userId);

    @Query("SELECT count(appointmentId) FROM appointment where user_id =:userId and status = 1")
    int totalApprovedAppointment(int userId);

}