package com.jeanboy.domain.features.user;

import com.jeanboy.data.cache.database.model.UserModel;

import java.util.List;

/**
 * Created by jeanboy on 2017/7/31.
 */

public class UserContract {

    public interface View {

        void onInfoChange(UserModel userModel);

        void onInfoError();

        void onFriendListChange(List<UserModel> friendList);

        void onFriendListError();

    }

    public interface Presenter {

        void getInfo(String accessToken, String userId);

        void getFriendList(String accessToken, String userId, int skip, int limit);
    }
}
