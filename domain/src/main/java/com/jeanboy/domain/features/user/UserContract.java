package com.jeanboy.domain.features.user;

import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.domain.base.BasePresenter;

import java.util.List;

/**
 * Created by jeanboy on 2017/7/31.
 */

public class UserContract {

    public interface View {

        void getInfoSuccess(UserModel userModel);

        void getInfoError();

        void getFriendListSuccess(List<UserModel> friendList);

        void getFriendListError();

    }

    public interface Presenter extends BasePresenter<View> {

        void getInfo(String accessToken, String userId);

        void getFriendList(String accessToken, String userId, int skip, int limit);
    }
}
