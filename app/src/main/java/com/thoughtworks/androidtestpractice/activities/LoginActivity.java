package com.thoughtworks.androidtestpractice.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.thoughtworks.androidtestpractice.R;
import com.thoughtworks.androidtestpractice.MyApplication;
import com.thoughtworks.androidtestpractice.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    public LoginViewModel loginViewModel;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = obtainViewModel();
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(view -> login());

        loginViewModel.observerLoginViewModel(this, loginResult ->
                Toast.makeText(this, loginResult.getResult(), Toast.LENGTH_SHORT).show());
    }

    private void login() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        loginViewModel.login(username, password);
    }

    private LoginViewModel obtainViewModel() {
        MyApplication application = (MyApplication)getApplication();
        LoginViewModel loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.setUserRepository(application.getUserRepository());
        return  loginViewModel;
    }
}