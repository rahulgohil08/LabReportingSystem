package com.example.myproject.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myproject.database.dao.AdminDao;
import com.example.myproject.database.dao.AppointmentDao;
import com.example.myproject.database.dao.LabDao;
import com.example.myproject.database.dao.TestDao;
import com.example.myproject.database.dao.UserDao;
import com.example.myproject.database.entities.Admin;
import com.example.myproject.database.entities.Appointment;
import com.example.myproject.database.entities.Lab;
import com.example.myproject.database.entities.Test;
import com.example.myproject.database.entities.User;

@Database(entities =
        {
                Admin.class,
                User.class,
                Lab.class,
                Test.class,
                Appointment.class,
        },
        version = 1)

public abstract class AppDatabase extends RoomDatabase {
    public abstract AdminDao adminDao();

    public abstract UserDao userDao();

    public abstract LabDao labDao();

    public abstract TestDao testDao();

    public abstract AppointmentDao appointmentDao();
}