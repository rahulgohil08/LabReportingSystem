package com.example.myproject.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "appointment")

public class Appointment {
    @PrimaryKey(autoGenerate = true)
    public int appointmentId;

    @ColumnInfo(name = "user_id")
    public int userId;

    @ColumnInfo(name = "datetime")
    public String datetime;

    @ColumnInfo(name = "status")
    public int status = 0;  // 0 pending, 1 = approved, 2 = rejected

    public Appointment(int userId, String datetime) {
        this.userId = userId;
        this.datetime = datetime;
     }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}