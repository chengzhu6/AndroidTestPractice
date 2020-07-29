package com.thoughtworks.androidtestpractice.dbdatasource;

import com.thoughtworks.androidtestpractice.common.AppDatabase;
import com.thoughtworks.androidtestpractice.entities.User;

import io.reactivex.Maybe;

public class UserDBDataSource {

    private AppDatabase myDatabase;

    public UserDBDataSource(AppDatabase myDatabase) {
        this.myDatabase = myDatabase;
    }

    public Maybe<User> findByName(String name) {
        return myDatabase.userDao().findByName(name);
    }
}
