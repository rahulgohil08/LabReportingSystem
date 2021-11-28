package com.example.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.adapters.AppointmentAdapter;
import com.example.myproject.adapters.LabAdapter;
import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.AppointmentDao;
import com.example.myproject.database.dao.LabDao;
import com.example.myproject.database.entities.Lab;
import com.example.myproject.databinding.ActivityAppointmentListBinding;
import com.example.myproject.utils.SharedPrefManager;
import com.example.myproject.database.entities.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentListActivity extends AppCompatActivity {

    private ActivityAppointmentListBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;
    private List<Appointment> appointmentList = new ArrayList<>();
    private AppointmentAdapter appointmentAdapter;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAppointmentListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();


        sharedPrefManager = new SharedPrefManager(context);

        getSupportActionBar().setTitle("Lab List");
        init();

    }


    private void init() {
        appointmentAdapter = new AppointmentAdapter(context, appointmentList);
        AppointmentDao dao = db.appointmentDao();

        binding.recyclerView.setAdapter(appointmentAdapter);

        new Thread(() -> {
            appointmentList.clear();
            appointmentList.addAll(dao.getAll());
            runOnUiThread(() -> {
                appointmentAdapter.notifyDataSetChanged();
            });
        }).start();


        binding.fab.setOnClickListener(view -> {
            openActivity(CreateAppointmentActivity.class);
        });
    }



    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);
    }
}