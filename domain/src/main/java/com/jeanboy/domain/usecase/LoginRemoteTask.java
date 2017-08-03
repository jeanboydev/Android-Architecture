package com.jeanboy.domain.usecase;

import com.jeanboy.base.manager.net.RequestCallback;
import com.jeanboy.base.manager.net.ResponseData;
import com.jeanboy.data.cache.database.model.TokenModel;
import com.jeanboy.data.net.entity.TokenEntity;
import com.jeanboy.data.net.mapper.TokenDataMapper;
import com.jeanboy.data.repository.Injection;
import com.jeanboy.data.repository.UserRepository;
import com.jeanboy.domain.base.BaseUseCase;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class LoginRemoteTask extends BaseUseCase<LoginRemoteTask.RequestValues, LoginRemoteTask.ResponseValues> {

    private final UserRepository userRepository = Injection.provideUserRepository();
    private Call<TokenEntity> call;

    public LoginRemoteTask() {
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        call = userRepository.login(requestValues.getUsername(), requestValues.getPassword(), new
                RequestCallback<ResponseData<TokenEntity>>() {
                    @Override
                    public void onSuccess(ResponseData<TokenEntity> response) {
                        TokenEntity body = response.getBody();
                        if (body == null) return;
                        // TODO: 2017/7/28 mapper数据转换层
                        TokenModel tokenModel = new TokenDataMapper().transform(body);
                        if (getUseCaseCallback() == null) return;
                        getUseCaseCallback().onSuccess(new ResponseValues(tokenModel));
                    }

                    @Override
                    public void onError(int code, String msg) {
                        if (getUseCaseCallback() == null) return;
                        getUseCaseCallback().onError();
                    }
                });
    }

    @Override
    protected void cancelUseCase() {
        if (call != null) {
            call.cancel();
        }
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
