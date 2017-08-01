package com.jeanboy.domain.features.user;

import com.jeanboy.domain.base.BaseUseCase;
import com.jeanboy.domain.usecase.GetFriendListRemoteTask;
import com.jeanboy.domain.usecase.GetInfoRemoteTask;

/**
 * Created by jeanboy on 2017/7/31.
 */

public class UserPresenter implements UserContract.Presenter {

    private UserContract.View view;

    private GetInfoRemoteTask getInfoRemoteTask = new GetInfoRemoteTask();
    private GetFriendListRemoteTask getFriendListRemoteTask = new GetFriendListRemoteTask();

    @Override
    public void setView(UserContract.View view) {
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.view = null;
        if (getInfoRemoteTask != null) {
            getInfoRemoteTask.cancel();
        }
        if (getFriendListRemoteTask != null) {
            getFriendListRemoteTask.cancel();
        }
    }

    @Override
    public void getInfo(String accessToken, String userId) {
        getInfoRemoteTask.setRequestValues(new GetInfoRemoteTask.RequestValues(accessToken, userId));
        getInfoRemoteTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<GetInfoRemoteTask.ResponseValues>() {
            @Override
            public void onSuccess(GetInfoRemoteTask.ResponseValues response) {
                if (view == null) return;
                view.getInfoSuccess(response.getUserModel());
            }

            @Override
            public void onError() {
                if (view == null) return;
                view.getInfoError();
            }
        });
        getInfoRemoteTask.run();
    }

    @Override
    public void getFriendList(String accessToken, String userId, int skip, int limit) {
        getFriendListRemoteTask.setRequestValues(new GetFriendListRemoteTask.RequestValues(accessToken, userId, skip, limit));
        getFriendListRemoteTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<GetFriendListRemoteTask.ResponseValues>() {
            @Override
            public void onSuccess(GetFriendListRemoteTask.ResponseValues response) {
                if (view == null) return;
                view.getFriendListSuccess(response.getUserModel());
            }

            @Override
            public void onError() {
                if (view == null) return;
                view.getFriendListError();
            }
        });
        getFriendListRemoteTask.run();
    }
}
