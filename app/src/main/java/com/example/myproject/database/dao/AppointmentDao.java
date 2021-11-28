package com.example.myproject.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myproject.database.entities.Appointment;

import java.util.List;

@Dao
public interface AppointmentDao {

    @Query("SELECT * FROM appointment")
    List<Appointment> getAll();

    @Query("SELECT count(appointmentId) FROM appointment")
    int getAppointmentCount();

    @Query("SELECT * FROM appointment WHERE appointmentId = :appointmentId")
    Appointment getAppointmentById(int appointmentId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertAppointment(Appointment appointment);

    @Update
    void updateAppointment(Appointment appointment);

    @Query("DELETE FROM appointment WHERE appointmentId = :appointmentId")
    int deleteAppointment(int appointmentId);
}