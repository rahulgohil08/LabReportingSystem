package com.example.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.adapters.UserAdapter;
import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.UserDao;
import com.example.myproject.database.entities.User;
import com.example.myproject.databinding.ActivityUserDashboardBinding;
import com.example.myproject.databinding.ActivityUserListBinding;
import com.example.myproject.utils.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity implements UserAdapter.UserInterface {

    private ActivityUserListBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;
    private List<User> userList = new ArrayList<>();
    private UserAdapter userAdapter;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();


        sharedPrefManager = new SharedPrefManager(context);

        getSupportActionBar().setTitle("User List");
        init();

    }


    private void init() {
        userAdapter = new UserAdapter(context, userList, this);
        UserDao userDao = db.userDao();

        binding.recyclerView.setAdapter(userAdapter);

        new Thread(() -> {
            userList.clear();
            userList.addAll(userDao.getAllUsers());
            runOnUiThread(() -> {
                userAdapter.notifyDataSetChanged();
            });
        }).start();


    }


    @Override
    public void onClick(User user) {

    }
}