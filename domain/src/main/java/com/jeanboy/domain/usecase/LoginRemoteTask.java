package com.jeanboy.domain.usecase;

import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.data.net.entity.TokenEntity;
import com.jeanboy.data.net.mapper.TokenDataMapper;
import com.jeanboy.data.repository.UserRepository;
import com.jeanboy.domain.base.BaseUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class LoginRemoteTask extends BaseUseCase<LoginRemoteTask.RequestValues, LoginRemoteTask.ResponseValues> {

    private UserRepository userRepository;

    @Inject
    public LoginRemoteTask(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        userRepository.login(requestValues.getUsername(), requestValues.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TokenEntity>() {
                    @Override
                    public void accept(@NonNull TokenEntity body) throws Exception {
                        if (body == null) return;
                        // TODO: 2017/7/28 mapper数据转换层
                        TokenModel tokenModel = new TokenDataMapper().transform(body);
                        if (getUseCaseCallback() == null) return;
                        getUseCaseCallback().onSuccess(new ResponseValues(tokenModel));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (getUseCaseCallback() == null) return;
                        getUseCaseCallback().onError();
                    }
                });
    }

    @Override
    protected void cancelUseCase() {
    }

    public static final class RequestValues implements BaseUseCase.RequestValues {
        private final String username;
        private final String password;

        public RequestValues(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public static final class ResponseValues implements BaseUseCase.ResponseValues {

        private final TokenModel tokenModel;

        public ResponseValues(TokenModel tokenModel) {
            this.tokenModel = tokenModel;
        }

        public TokenModel getTokenModel() {
            return tokenModel;
        }
    }
}
