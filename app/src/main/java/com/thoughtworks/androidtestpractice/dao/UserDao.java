package com.thoughtworks.androidtestpractice.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.thoughtworks.androidtestpractice.entities.User;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE username=:name")
    Maybe<User> findByName(String name);

    @Insert
    Single<Long> save(User user);
}
