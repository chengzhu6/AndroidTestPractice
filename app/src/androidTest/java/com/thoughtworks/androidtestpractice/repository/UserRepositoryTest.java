package com.thoughtworks.androidtestpractice.repository;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.thoughtworks.androidtestpractice.common.AppDatabase;
import com.thoughtworks.androidtestpractice.dbdatasource.UserDBDataSource;
import com.thoughtworks.androidtestpractice.entities.User;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.schedulers.Schedulers;

public class UserRepositoryTest {

    private AppDatabase appDatabase;
    private UserRepository userRepository;

    @Before
    public void setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                AppDatabase.class).build();
        UserDBDataSource userDBDataSource = new UserDBDataSource(appDatabase);
        userRepository = new UserRepository(userDBDataSource);
    }

    @Test
    public void should_get_user() {
        User user = new User();
        user.id = 1;
        user.username = "1";
        user.password = "1";
        appDatabase.userDao().save(user).subscribeOn(Schedulers.io()).subscribe();
        userRepository.findByName("1")
                .test()
                .assertValue(user1 -> user1.id == user.id);
    }
}