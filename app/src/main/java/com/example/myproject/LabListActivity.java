package com.example.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.LabDao;
import com.example.myproject.databinding.ActivityLabListBinding;
import com.example.myproject.utils.SharedPrefManager;

public class LabListActivity extends AppCompatActivity {

    private ActivityLabListBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;
      AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLabListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();


        sharedPrefManager = new SharedPrefManager(context);

        getSupportActionBar().setTitle("Lab List");
        init();

    }


    private void init() {
         LabDao labDao = db.labDao();




    }



    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);
    }
}