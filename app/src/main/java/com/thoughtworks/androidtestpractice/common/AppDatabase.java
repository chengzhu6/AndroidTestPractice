package com.thoughtworks.androidtestpractice.common;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.thoughtworks.androidtestpractice.dao.UserDao;
import com.thoughtworks.androidtestpractice.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
