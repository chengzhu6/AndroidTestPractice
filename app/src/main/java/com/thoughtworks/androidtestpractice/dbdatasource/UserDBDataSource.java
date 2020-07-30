package com.thoughtworks.androidtestpractice.dbdatasource;

import com.thoughtworks.androidtestpractice.common.AppDatabase;
import com.thoughtworks.androidtestpractice.dao.entities.User;

import io.reactivex.Maybe;
import io.reactivex.Single;

public class UserDBDataSource {

    private AppDatabase myDatabase;

    public UserDBDataSource(AppDatabase myDatabase) {
        this.myDatabase = myDatabase;
    }

    public Maybe<User> findByName(String name) {
        return myDatabase.userDao().findByName(name);
    }

    public Single<Long> save(User user) {
        return myDatabase.userDao().save(user);
    }
}
