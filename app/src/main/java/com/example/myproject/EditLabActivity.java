package com.example.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.LabDao;
import com.example.myproject.database.entities.Lab;
import com.example.myproject.databinding.ActivityEditLabBinding;
import com.example.myproject.utils.SharedPrefManager;

public class EditLabActivity extends AppCompatActivity {

    private ActivityEditLabBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;

    AppDatabase db;
    String password = "password";
    int labId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditLabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();


        sharedPrefManager = new SharedPrefManager(context);

        Intent intent = getIntent();
        labId = intent.getIntExtra("lab_id", 0);

        getSupportActionBar().setTitle("Edit Lab");
        init();

    }


    private void init() {

        binding.btnSubmit.setOnClickListener(v -> {

            String Name = binding.edtName.getEditText().getText().toString();
            String Email = binding.edtEmail.getEditText().getText().toString();
            String Mobile = binding.edtMobile.getEditText().getText().toString();


            editLab(Name, Email, Mobile);

        });


        LabDao labDao = db.labDao();


        new Thread(() -> {
            Lab lab = labDao.getLabById(labId);

            runOnUiThread(() -> {
                binding.edtName.getEditText().setText(lab.getName());
                binding.edtEmail.getEditText().setText(lab.getEmail());
                binding.edtMobile.getEditText().setText(lab.getMobileNo());

                password = lab.getPassword();
            });

        }).start();

    }


    private void editLab(String Name, String Email, String Mobile) {

        LabDao labDao = db.labDao();

        new Thread(() -> {
            Lab lab = new Lab(Name, Email, Mobile, password);
            lab.setLabId(labId);
            labDao.updateLab(lab);

            finish();
        }).start();
    }


}