package com.example.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.LabDao;
import com.example.myproject.database.dao.UserDao;
import com.example.myproject.databinding.ActivityAdminDashboardBinding;
import com.example.myproject.utils.SharedPrefManager;

public class AdminDashboardActivity extends AppCompatActivity {

    private ActivityAdminDashboardBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();

        binding = ActivityAdminDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefManager = new SharedPrefManager(context);

        getSupportActionBar().setTitle("Admin Dashboard");
        init();

    }


    private void init() {
        UserDao userDao = db.userDao();
        LabDao labDao = db.labDao();

        new Thread(() -> {
            int userCount = userDao.getUserCount();
            int labCount = labDao.getLabCount();
            runOnUiThread(() -> {
                binding.tvTotalUsers.setText("" + userCount);
                binding.tvTotalEmployee.setText("" + labCount);
            });
        }).start();

    }

//
//    public void onclick(View View) {
//        Intent intent = new Intent(AdminDashboardActivity.this, Availlabletest.class);
//        startActivity(intent);
//    }


    @Override
    protected void onStart() {
        super.onStart();

        boolean isLogin = sharedPrefManager.getBoolean("is_login");

        if (!isLogin) {
            openActivity(AdminDashboardActivity.class);
            finish();
        }
    }

    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);
    }

    public void logout(View view) {
        sharedPrefManager.clear();
        openActivity(LoginActivity.class);
        finish();
    }


    public void userList(View view) {
        openActivity(UserListActivity.class);
    }

    public void employeeList(View view) {
        openActivity(LabListActivity.class);
    }
}