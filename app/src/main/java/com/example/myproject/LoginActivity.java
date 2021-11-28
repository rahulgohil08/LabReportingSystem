package com.example.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myproject.database.AppDatabase;
import com.example.myproject.database.dao.AdminDao;
import com.example.myproject.database.dao.UserDao;
import com.example.myproject.database.entities.Admin;
import com.example.myproject.database.entities.User;
import com.example.myproject.databinding.ActivityLoginBinding;
import com.example.myproject.utils.Config;
import com.example.myproject.utils.SharedPrefManager;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Context context = this;
    private SharedPrefManager sharedPrefManager;
    private String userType = "admin";

    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "my-project").build();

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefManager = new SharedPrefManager(context);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Login");

        init();
    }


    private void init() {

        AdminDao adminDao = db.adminDao();
        new Thread(() -> {
            adminDao.deleteAll();
            adminDao.insertAdmin(new Admin("Admin", "admin@gmail.com", "password"));
        }).start();

        binding.radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {

            if (i == R.id.radioAdmin) {
                userType = "admin";
            } else if (i == R.id.radioPatient) {
                userType = "user";
            } else if (i == R.id.radioLab) {
                userType = "Lab";
            }
        });

        binding.button.setOnClickListener(view -> {
            String Email = binding.edtEmail.getText().toString();
            String Password = binding.edtPassword.getText().toString();


            doLogin(Email, Password);

        });


        binding.textViewNote.setOnClickListener(v -> {
            openActivity(RegisterActivity.class);
        });
    }

    private void doLogin(String email, String password) {

        UserDao userDao = db.userDao();
        AdminDao adminDao = db.adminDao();

        if (userType.equals("admin")) {

            new Thread(() -> {
                Admin admin = adminDao.loginAdmin(email, password);
                runOnUiThread(() -> {
                    if (admin != null) {

                        sharedPrefManager.setBoolean("is_login", true);
                        sharedPrefManager.setString("role", "admin");
                        sharedPrefManager.setInt("id", admin.getAdminId());
                        openActivity(AdminDashboardActivity.class);

                    } else {
                        Config.showToast(context, "Login fail");
                    }
                });
            }).start();


        } else if (userType.equals("user")) {

            new Thread(() -> {
                User user = userDao.loginUser(email, password);
                runOnUiThread(() -> {
                    if (user != null) {
                        sharedPrefManager.setBoolean("is_login", true);
                        sharedPrefManager.setString("role", "user");
                        sharedPrefManager.setInt("id", user.getUserId());

                        openActivity(UserDashboardActivity.class);
                    } else {
                        Config.showToast(context, "Login fail");
                    }
                });
            }).start();


        }

    }

    public void onclick(View View) {
        Intent intent = new Intent(LoginActivity.this, Availlabletest.class);
        startActivity(intent);
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