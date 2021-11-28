package com.example.myproject;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.AppointmentDao;
import com.example.myproject.database.dao.LabDao;
import com.example.myproject.database.entities.Appointment;
import com.example.myproject.database.entities.Lab;
import com.example.myproject.databinding.ActivityCreateAppointmentBinding;
import com.example.myproject.databinding.ActivityCreateLabBinding;
import com.example.myproject.utils.Config;
import com.example.myproject.utils.SharedPrefManager;

public class CreateAppointmentActivity extends AppCompatActivity {

    private ActivityCreateAppointmentBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;
    AppDatabase db;

    private String date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateAppointmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();


        sharedPrefManager = new SharedPrefManager(context);

        getSupportActionBar().setTitle("Create Appointment");
        init();

    }


    private void init() {

        binding.edtDate.getEditText().setOnClickListener(view -> {
            pickDate();
        });


        binding.btnSubmit.setOnClickListener(v -> {

            if (TextUtils.isEmpty(date)) {
                Config.showToast(context, "choose date");
                return;
            }
            createAppointment(date);
        });
    }

    private void pickDate() {

    }


    private void createAppointment(String date) {

        AppointmentDao dao = db.appointmentDao();

        new Thread(() -> {
            dao.insertAppointment(new Appointment(
                    sharedPrefManager.getInt("id"),
                    date
            ));

            finish();
        }).start();
    }


}