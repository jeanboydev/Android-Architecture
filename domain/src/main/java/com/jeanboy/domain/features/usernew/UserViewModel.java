package com.jeanboy.domain.features.usernew;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.repository.UserRepository;
import com.jeanboy.data.repository.handler.RepositoryCallback;

/**
 * Created by jeanboy on 2017/9/29.
 */

public class UserViewModel extends ViewModel {

    private LiveData<TokenModel> tokenData;
    private LiveData<UserModel> userData;

    private MutableLiveData<String> username = new MutableLiveData<>();

    private UserRepository userRepository = new UserRepository();

    public UserViewModel() {

        tokenData = Transformations.switchMap(username, new Function<String, LiveData<TokenModel>>() {
            @Override
            public LiveData<TokenModel> apply(String input) {
                return userRepository.login(input, "", null);
            }
        });
    }

    public void login(String username, final String password, RepositoryCallback<TokenModel> callback) {
        if (tokenData != null) return;
        tokenData = userRepository.login(username, password, callback);
    }

    public void getInfo(final String accessToken, final String userId, RepositoryCallback<UserModel> callback) {
        if (userData != null) return;
        userData = userRepository.getInfo(accessToken, userId, callback);
    }

    public LiveData<TokenModel> getTokenData() {
        return tokenData;
    }

    public LiveData<UserModel> getUserData() {
        return userData;
    }
}
