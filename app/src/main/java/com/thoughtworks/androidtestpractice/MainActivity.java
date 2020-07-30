package com.thoughtworks.androidtestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.thoughtworks.androidtestpractice.activities.LoginActivity;
import com.thoughtworks.androidtestpractice.common.AppDatabase;
import com.thoughtworks.androidtestpractice.dbdatasource.UserDBDataSource;
import com.thoughtworks.androidtestpractice.dao.entities.User;

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
        user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        user.setUsername("android");
        AppDatabase database = application.getDatabase();
        UserDBDataSource userDBDataSource = new UserDBDataSource(database);
        userDBDataSource.save(user).subscribeOn(Schedulers.io()).subscribe();
    }

    private void createLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}