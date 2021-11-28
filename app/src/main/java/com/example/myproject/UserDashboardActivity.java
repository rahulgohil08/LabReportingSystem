package com.example.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.UserDao;
import com.example.myproject.database.entities.Admin;
import com.example.myproject.databinding.ActivityAdminDashboardBinding;
import com.example.myproject.databinding.ActivityUserDashboardBinding;
import com.example.myproject.utils.Config;
import com.example.myproject.utils.SharedPrefManager;

public class UserDashboardActivity extends AppCompatActivity {

    private ActivityUserDashboardBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();


        sharedPrefManager = new SharedPrefManager(context);

        getSupportActionBar().setTitle("User Dashboard");
        init();

    }


    private void init() {
        UserDao userDao = db.userDao();

        new Thread(() -> {
            int pendingAppointment = userDao.totalPendingAppointment(sharedPrefManager.getInt("id"));
            int approvedAppointment = userDao.totalPendingAppointment(sharedPrefManager.getInt("id"));

            runOnUiThread(() -> {
                binding.tvPendingAppointment.setText("" + pendingAppointment);
                binding.tvApprovedAppointment.setText("" + approvedAppointment);
            });
        }).start();

    }


    public void onclick(View View) {
        Intent intent = new Intent(UserDashboardActivity.this, Availlabletest.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();

        boolean isLogin = sharedPrefManager.getBoolean("is_login");

        if (!isLogin) {
            openActivity(UserDashboardActivity.class);
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

    public void manageAppointment(View view) {
        openActivity(UserListActivity.class);
    }
}