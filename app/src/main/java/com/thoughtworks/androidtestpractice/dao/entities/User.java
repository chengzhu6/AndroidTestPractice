package com.thoughtworks.androidtestpractice.dao.entities;

import androidx.annotation.VisibleForTesting;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;
    private String password;

    public int getId() {
        return id;
    }

    @VisibleForTesting
    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @VisibleForTesting
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @VisibleForTesting
    public void setPassword(String password) {
        this.password = password;
    }

    public static User getDefaultUser() {
        return DEFAULT_USER;
    }

    public final static User DEFAULT_USER = new User();
}
