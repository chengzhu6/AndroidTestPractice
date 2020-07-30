package com.thoughtworks.androidtestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.thoughtworks.androidtestpractice.activities.LoginActivity;
import com.thoughtworks.androidtestpractice.entities.User;

import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.login_activity).setOnClickListener(view -> createLoginActivity());

        findViewById(R.id.insert).setOnClickListener(view -> insertUser());
    }

    private void insertUser() {
        MyApplication application = (MyApplication)getApplication();
        User user = new User();
        user.password = "e10adc3949ba59abbe56e057f20f883e";
        user.username = "android";
        application.getDatabase().userDao().save(user).subscribeOn(Schedulers.io()).subscribe();
    }

    private void createLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}