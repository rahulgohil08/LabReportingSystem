package com.example.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.adapters.LabAdapter;
import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.LabDao;
import com.example.myproject.database.entities.Lab;
import com.example.myproject.databinding.ActivityLabListBinding;
import com.example.myproject.utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class LabListActivity extends AppCompatActivity implements LabAdapter.LabInterface {

    private ActivityLabListBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;
    private List<Lab> labList = new ArrayList<>();
    private LabAdapter labAdapter;
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
        labAdapter = new LabAdapter(context, labList, this);
        LabDao labDao = db.labDao();

        binding.recyclerView.setAdapter(labAdapter);

        new Thread(() -> {
            labList.clear();
            labList.addAll(labDao.getAll());
            runOnUiThread(() -> {
                labAdapter.notifyDataSetChanged();
            });
        }).start();


        binding.fab.setOnClickListener(view -> {
            openActivity(CreateLabActivity.class);
        });
    }


    @Override
    public void onEdit(Lab lab) {
        Intent intent = new Intent(context, EditLabActivity.class);
        intent.putExtra("lab_id",lab.labId);
        startActivity(intent);
    }

    @Override
    public void onDelete(Lab lab) {
        LabDao labDao = db.labDao();

        new Thread(() -> {
            labDao.deleteLab(lab.labId);
            labList.clear();
            labList.addAll(labDao.getAll());
            runOnUiThread(() -> {
                labAdapter.notifyDataSetChanged();
            });
        }).start();
    }

    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);
    }
}