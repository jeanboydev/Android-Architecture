package com.jeanboy.domain.usecase;

import com.jeanboy.data.cache.database.model.UserModel;
import com.jeanboy.data.repository.UserRepository;
import com.jeanboy.domain.base.BaseUseCase;

import javax.inject.Inject;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class GetInfoRemoteTask extends BaseUseCase<GetInfoRemoteTask.RequestValues, GetInfoRemoteTask.ResponseValues> {

    private final UserRepository userRepository;

    @Inject
    public GetInfoRemoteTask(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
//        userRepository.getInfo(requestValues.getAccessToken(), requestValues.getUserId())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<UserEntity>() {
//                    @Override
//                    public void accept(@NonNull UserEntity body) throws Exception {
//                        if (body == null) return;
//                        // TODO: 2017/7/28 mapper数据转换层
//                        UserModel userModel = new UserDataMapper().transform(body);
//                        if (getUseCaseCallback() == null) return;
//                        getUseCaseCallback().onSuccess(new ResponseValues(userModel));
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

        public RequestValues(String accessToken, String userId) {
            this.accessToken = accessToken;
            this.userId = userId;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public String getUserId() {
            return userId;
        }
    }

    public static final class ResponseValues implements BaseUseCase.ResponseValues {

        private final UserModel userModel;

        public ResponseValues(UserModel userModel) {
            this.userModel = userModel;
        }

        public UserModel getUserModel() {
            return userModel;
        }
    }
}
