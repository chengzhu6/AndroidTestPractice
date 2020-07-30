package com.thoughtworks.androidtestpractice.repository;

import com.thoughtworks.androidtestpractice.dbdatasource.UserDBDataSource;
import com.thoughtworks.androidtestpractice.entities.User;

import io.reactivex.Maybe;

public class UserRepository {
    private UserDBDataSource userDBDataSource;


    public UserRepository(UserDBDataSource userDBDataSource) {
        this.userDBDataSource = userDBDataSource;
    }

    public Maybe<User> findByName(String name) {
        return userDBDataSource.findByName(name);
    }
}
