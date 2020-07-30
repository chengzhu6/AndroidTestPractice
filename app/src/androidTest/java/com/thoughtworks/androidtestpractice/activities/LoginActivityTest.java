package com.thoughtworks.androidtestpractice.activities;

import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.thoughtworks.androidtestpractice.MyApplication;
import com.thoughtworks.androidtestpractice.R;
import com.thoughtworks.androidtestpractice.dao.entities.User;
import com.thoughtworks.androidtestpractice.repository.UserRepository;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.internal.operators.maybe.MaybeCreate;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class LoginActivityTest {

    private static UserRepository userRepository;

    @BeforeClass
    public static void beforeClass() {
        MyApplication appContext = (MyApplication)InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getApplicationContext();
        userRepository = appContext.getUserRepository();
    }

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void should_login_failure_when_username_not_exist() {
        when(userRepository.findByName("not exist user")).thenReturn(new MaybeCreate<>(MaybeEmitter::onComplete));
        onView(withId(R.id.username))
                .perform(typeText("not exist user"));
        onView(withId(R.id.password))
                .perform(typeText("123456"));
        onView(withId(R.id.login))
                .perform(click());

        onView(withText(R.string.username_not_exist_toast))
                .inRoot(withDecorView(not(is(loginActivityActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void should_login_failure_when_username_and_password_is_incorrect() {
        User user = new User();
        user.setUsername("android");
        user.setPassword("123456");
        when(userRepository.findByName("android")).thenReturn(Maybe.just(user));
        onView(withId(R.id.username))
                .perform(typeText("android"));
        onView(withId(R.id.password))
                .perform(typeText("1234567"));
        onView(withId(R.id.login))
                .perform(click());

        onView(withText(R.string.password_invalid_toast))
                .inRoot(withDecorView(not(is(loginActivityActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void should_login_success_when_username_and_password_is_correct() {
        User user = new User();
        user.setUsername("android");
        user.setPassword("e10adc3949ba59abbe56e057f20f883e");
        when(userRepository.findByName("android")).thenReturn(Maybe.just(user));
        onView(withId(R.id.username))
                .perform(typeText("android"));
        onView(withId(R.id.password))
                .perform(typeText("123456"));
        onView(withId(R.id.login))
                .perform(click());

        onView(withText(R.string.login_success_toast))
                .inRoot(withDecorView(not(is(loginActivityActivityTestRule.getActivity().getWindow().getDecorView()))))
                .check(ViewAssertions.matches(isDisplayed()));
    }
}