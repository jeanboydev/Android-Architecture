package com.jeanboy.domain.features.token;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.data.repository.UserRepository;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2017/10/17.
 */

public class TokenViewModel extends ViewModel {

    private MediatorLiveData<TokenModel> tokenData;

    private LiveData<TokenModel> tokenSource;

    @Inject
    UserRepository userRepository;

    public TokenViewModel() {

        tokenData = new MediatorLiveData<>();

        LiveData<TokenModel> cacheToken = userRepository.getToken();
        if (cacheToken != null) {
            tokenData.setValue(cacheToken.getValue());
        }
    }

    public void login(String username, String password) {
        if (tokenSource != null) {
            tokenData.removeSource(tokenSource);
        }
        tokenSource = userRepository.getToken(username, password);
        tokenData.addSource(tokenSource, new Observer<TokenModel>() {
            @Override
            public void onChanged(@Nullable TokenModel tokenModel) {
                tokenData.setValue(tokenModel);
            }
        });
    }

    public LiveData<TokenModel> getTokenData() {
        return tokenData;
    }
}
