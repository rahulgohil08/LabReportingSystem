package com.example.myproject;


import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.LabDao;
import com.example.myproject.database.entities.Lab;
import com.example.myproject.databinding.ActivityCreateLabBinding;
import com.example.myproject.utils.SharedPrefManager;

public class CreateLabActivity extends AppCompatActivity {

    private ActivityCreateLabBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateLabBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();


        sharedPrefManager = new SharedPrefManager(context);

        getSupportActionBar().setTitle("Create Lab");
        init();

    }


    private void init() {

        binding.btnSubmit.setOnClickListener(v -> {

            String Name = binding.edtName.getEditText().getText().toString();
            String Email = binding.edtEmail.getEditText().getText().toString();
            String Mobile = binding.edtMobile.getEditText().getText().toString();
            String Password = binding.edtPassword.getEditText().getText().toString();


            createLab(Name, Email, Mobile, Password);

        });
    }


    private void createLab(String Name, String Email, String Mobile, String Password) {

        LabDao labDao = db.labDao();

        new Thread(() -> {
            labDao.insertLab(new Lab(
                    Name, Email, Mobile, Password
            ));
//            runOnUiThread(() -> {
//
//            });
            finish();
        }).start();
    }


}