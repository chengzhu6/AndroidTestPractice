package com.thoughtworks.androidtestpractice.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.androidtestpractice.R;
import com.thoughtworks.androidtestpractice.dao.entities.User;
import com.thoughtworks.androidtestpractice.repository.UserRepository;
import com.thoughtworks.androidtestpractice.utils.EncryptUtil;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {
    private final static String TAG = LoginViewModel.class.getName();
    private UserRepository userRepository;
    private MutableLiveData<LoginResult> loginResult;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void observerLoginViewModel(LifecycleOwner owner, Observer<LoginResult> observer) {
        getLoginResult().observe(owner, observer);
    }

    private MutableLiveData<LoginResult> getLoginResult() {
        if (loginResult == null) {
            loginResult = new MutableLiveData<>();
        }
        return loginResult;
    }

    public void login(@NonNull String username, @NonNull String password) {
        Disposable disposable = userRepository.findByName(username)
                .defaultIfEmpty(User.DEFAULT_USER)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(compositeDisposable::add)
                .subscribe(user -> {
                    if (user.equals(User.DEFAULT_USER)) {
                        Log.d(TAG, username + "is not exist");
                        loginResult.postValue(LoginResult.createUserNotExistResult());
                    } else if (user.getPassword().equals(EncryptUtil.getMD5(password))) {
                        loginResult.postValue(LoginResult.createSuccessResult());
                    } else {
                        loginResult.postValue(LoginResult.createPasswordInvalidResult());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public static class LoginResult {
        private int result;

        public LoginResult(int result) {
            this.result = result;
        }

        public static LoginResult createSuccessResult() {
            return new LoginResult(R.string.login_success_toast);
        }

        public static LoginResult createPasswordInvalidResult() {
            return new LoginResult(R.string.password_invalid_toast);
        }

        public static LoginResult createUserNotExistResult() {
            return new LoginResult(R.string.username_not_exist_toast);
        }

        public int getResult() {
            return result;
        }
    }
}

