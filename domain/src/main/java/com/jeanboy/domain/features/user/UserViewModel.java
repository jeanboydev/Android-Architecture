package com.jeanboy.domain.features.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2017/9/29.
 */

public class UserViewModel extends ViewModel {

    private MediatorLiveData<UserModel> userData;
    private MediatorLiveData<List<UserModel>> userFriendListData;

    private LiveData<UserModel> userSource;
    private LiveData<List<UserModel>> userFriendListSource;

    @Inject
    UserRepository userRepository;

    public UserViewModel() {

        userData = new MediatorLiveData<>();
        userFriendListData = new MediatorLiveData<>();

//        userData = Transformations.switchMap(userIdData, new Function<String, LiveData<UserModel>>() {
//            @Override
//            public LiveData<UserModel> apply(String input) {
//                if (tokenData == null) return null;
//                TokenModel value = tokenData.getValue();
//                if (value == null) return null;
//                return userRepository.getInfo(value.getAccessToken(), input);
//            }
//        });
    }


    public void getInfo(String accessToken, String userId) {
        if (userSource != null) {
            userData.removeSource(userSource);
        }
        userSource = userRepository.getInfo(accessToken, userId);
        userData.addSource(userSource, new Observer<UserModel>() {
            @Override
            public void onChanged(@Nullable UserModel userModel) {
                userData.setValue(userModel);
            }
        });
    }

    public void getFriendList(String accessToken, String userId, int skip, int limit) {
        if (userFriendListSource != null) {
            userFriendListData.removeSource(userFriendListSource);
        }
        userFriendListSource = userRepository.getFriendList(accessToken, userId, skip, limit);
        userFriendListData.addSource(userFriendListSource, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(@Nullable List<UserModel> userModels) {
                userFriendListData.setValue(userModels);
            }
        });
    }

    public LiveData<UserModel> getUserData() {
        return userData;
    }
}
