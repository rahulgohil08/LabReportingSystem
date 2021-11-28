package com.example.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.UserDao;
import com.example.myproject.database.entities.User;
import com.example.myproject.databinding.ActivityRegisterBinding;
import com.example.myproject.utils.Config;
import com.example.myproject.utils.SharedPrefManager;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;

    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefManager = new SharedPrefManager(context);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Register");

        init();
    }


    private void init() {

        binding.btnRegister.setOnClickListener(view -> {
            String Name = binding.edtName.getText().toString();
            String Email = binding.edtEmail.getText().toString();
            String Address = binding.edtAddress.getText().toString();
            String Mobile = binding.edtMobile.getText().toString();
            String Password = binding.edtPassword.getText().toString();


            doRegister(Name, Email, Password, Address, Mobile);

        });

    }

    private void doRegister(String Name, String Email, String Password, String Address, String Mobile) {

        UserDao userDao = db.userDao();

        new Thread(() -> {
            long id = userDao.insertUser(new User(
                    Name, Email, Mobile, Address, binding.spinner3.getSelectedItem().toString(), "000", Password
            ));
            Log.e("HEKKKK", "doRegister:" + id);

            runOnUiThread(() -> {

                Config.showToast(context, "Register done");
                finish();
//                        sharedPrefManager.setBoolean("is_login", true);
//                        sharedPrefManager.setString("role", "user");
//                        openActivity(AdminDashboardActivity.class);

            });
        }).start();


    }


    @Override
    protected void onStart() {
        super.onStart();

        boolean isLogin = sharedPrefManager.getBoolean("is_login");

        if (isLogin) {
            String userType = sharedPrefManager.getString("role");
            if (userType.equals("admin")) {
                openActivity(AdminDashboardActivity.class);
                finish();
            } else if (userType.equals("user")) {
                openActivity(AdminDashboardActivity.class);
                finish();
            } else if (userType.equals("lab")) {
                openActivity(AdminDashboardActivity.class);
                finish();
            }

        }
    }

    private void openActivity(Class aclass) {
        Intent intent = new Intent(context, aclass);
        startActivity(intent);
    }
}