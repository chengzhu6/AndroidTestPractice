package com.thoughtworks.androidtestpractice;

import android.app.Application;

import com.thoughtworks.androidtestpractice.repository.UserRepository;

import static org.mockito.Mockito.mock;



public class MyApplication extends Application {
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        if (userRepository == null) {
            userRepository = mock(UserRepository.class);
        }
        return userRepository;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
