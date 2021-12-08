package com.example.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.AppointmentDao;
import com.example.myproject.database.entities.Appointment;
import com.example.myproject.databinding.ActivityAppointmentListBinding;
import com.example.myproject.utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class AppointmentListActivity extends AppCompatActivity {

    private ActivityAppointmentListBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;
    private List<Appointment> appointmentList = new ArrayList<>();
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAppointmentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();


        sharedPrefManager = new SharedPrefManager(context);

        getSupportActionBar().setTitle("Appointment List");
        init();

    }


    private void init() {
        AppointmentDao dao = db.appointmentDao();


    }


    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);
    }
}