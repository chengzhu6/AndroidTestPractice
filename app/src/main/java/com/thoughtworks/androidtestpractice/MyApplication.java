package com.thoughtworks.androidtestpractice;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.thoughtworks.androidtestpractice.common.AppDatabase;
import com.thoughtworks.androidtestpractice.dbdatasource.UserDBDataSource;
import com.thoughtworks.androidtestpractice.repository.UserRepository;

public class MyApplication extends Application {
    private Context context;
    private AppDatabase database;
    private UserRepository userRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        this.database = Room.databaseBuilder(this.context, AppDatabase.class, "MyDatabase").build();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public UserRepository getUserRepository() {
        if (userRepository == null) {
            UserDBDataSource userDBDataSource = new UserDBDataSource(database);
            userRepository = new UserRepository(userDBDataSource);
        }
        return userRepository;
    }

    @Override
    public void onTerminate() {
        database.close();
        super.onTerminate();
    }
}
