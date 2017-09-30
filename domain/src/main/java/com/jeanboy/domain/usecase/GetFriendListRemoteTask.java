package com.jeanboy.domain.usecase;

import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.repository.UserRepository;
import com.jeanboy.domain.base.BaseUseCase;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class GetFriendListRemoteTask extends BaseUseCase<GetFriendListRemoteTask.RequestValues, GetFriendListRemoteTask.ResponseValues> {

    private final UserRepository userRepository;

    @Inject
    public GetFriendListRemoteTask(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
//        userRepository.getFriendList(requestValues.getAccessToken(), requestValues.getUserId(), requestValues.getSkip(),
//                requestValues.getLimit())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<UserEntity>>() {
//
//                    @Override
//                    public void accept(@NonNull List<UserEntity> body) throws Exception {
//                        // TODO: 2017/7/28 mapper数据转换层
//                        List<UserModel> modelList = new UserDataMapper().transform(body);
//                        if (getUseCaseCallback() == null) return;
//                        getUseCaseCallback().onSuccess(new ResponseValues(modelList));
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        if (getUseCaseCallback() == null) return;
//                        getUseCaseCallback().onError();
//                    }
//                });
    }

    @Override
    protected void cancelUseCase() {
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {
        private final String accessToken;
        private final String userId;
        private final int skip;
        private final int limit;

        public RequestValues(String accessToken, String userId, int skip, int limit) {
            this.accessToken = accessToken;
            this.userId = userId;
            this.skip = skip;
            this.limit = limit;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public String getUserId() {
            return userId;
        }

        public int getSkip() {
            return skip;
        }

        public int getLimit() {
            return limit;
        }
    }

    public static final class ResponseValues implements BaseUseCase.ResponseValues {

        private final List<UserModel> userModel;

        public ResponseValues(List<UserModel> userModel) {
            this.userModel = userModel;
        }

        public List<UserModel> getUserModel() {
            return userModel;
        }
    }
}
