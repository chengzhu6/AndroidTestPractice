package com.thoughtworks.androidtestpractice.repository;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.thoughtworks.androidtestpractice.common.AppDatabase;
import com.thoughtworks.androidtestpractice.dao.entities.User;
import com.thoughtworks.androidtestpractice.dbdatasource.UserDBDataSource;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.schedulers.Schedulers;

public class UserRepositoryTest {

    private UserRepository userRepository;
    private UserDBDataSource userDBDataSource;

    @Before
    public void setUp() {
        AppDatabase appDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                AppDatabase.class).build();
        userDBDataSource = new UserDBDataSource(appDatabase);
        userRepository = new UserRepository(userDBDataSource);
    }

    @Test
    public void should_get_user() {
        String testUsername = "username";
        User savedUser = new User();
        savedUser.setId(1);
        savedUser.setUsername(testUsername);
        savedUser.setPassword("password");
        userDBDataSource.save(savedUser).subscribeOn(Schedulers.io()).subscribe();
        userRepository.findByName(testUsername)
                .test()
                .assertValue(user1 -> user1.getId() == savedUser.getId());
    }
}