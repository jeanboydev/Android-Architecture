package com.jeanboy.domain.features.usernew;

import com.jeanboy.data.cache.database.model.UserModel;

import java.util.List;

/**
 * Created by jeanboy on 2017/7/31.
 */

public class UserContract {

    public interface View {

        void onLoginError();

        void onInfoChange(UserModel userModel);

        void onInfoError();

        void onFriendListChange(List<UserModel> friendList);

        void onFriendListError();

    }

    public interface PresenterNew {

        void login(String username, String password);

        void getInfo(String userId);

        void getFriendList(String userId, int skip, int limit);
    }
}
