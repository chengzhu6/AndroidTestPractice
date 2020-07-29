package com.thoughtworks.androidtestpractice.common;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.thoughtworks.androidtestpractice.dbdatasource.UserDBDataSource;
import com.thoughtworks.androidtestpractice.repository.UserRepository;

public class MyApplication extends Application {
    private Context context;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        this.database = Room.databaseBuilder(this.context, AppDatabase.class, "MyDatabase").build();
    }

    public UserRepository getUserRepository() {
        UserDBDataSource userDBDataSource = new UserDBDataSource(database);
        return new UserRepository(userDBDataSource);
    }

    @Override
    public void onTerminate() {
        database.close();
        super.onTerminate();
    }
}
