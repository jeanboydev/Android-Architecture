package com.jeanboy.domain.usecase;

import com.jeanboy.data.repository.UserRepository;
import com.jeanboy.domain.base.BaseUseCase;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class LoginRemoteTask extends BaseUseCase<LoginRemoteTask.RequestValues, LoginRemoteTask.ResponseValues> {

    private UserRepository userRepository;

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        userRepository.login(requestValues.getUsername(),requestValues.getPassword());
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

        public ResponseValues() {
        }
    }
}
