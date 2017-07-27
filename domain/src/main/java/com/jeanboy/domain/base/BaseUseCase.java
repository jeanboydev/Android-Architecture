package com.jeanboy.domain.base;

/**
 * Created by jeanboy on 2017/3/10.
 */

public abstract class BaseUseCase<Q extends BaseUseCase.RequestValues, P extends BaseUseCase.ResponseValues> {

    private Q mRequestValues;

    private UseCaseCallback<P> mUseCaseCallback;

    public void setRequestValues(Q requestValues) {
        mRequestValues = requestValues;
    }

    public Q getRequestValues() {
        return mRequestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return mUseCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P> useCaseCallback) {
        mUseCaseCallback = useCaseCallback;
    }

    public void run() {
        executeUseCase(mRequestValues);
    }

    public void cancel() {
        cancelUseCase();
    }

    protected abstract void executeUseCase(Q requestValues);

    protected abstract void cancelUseCase();

    /**
     * Data passed to a request.
     */
    public interface RequestValues {
    }

    /**
     * Data received from a request.
     */
    public interface ResponseValues {
    }

    public interface UseCaseCallback<R> {

        void onSuccess(R response);

        void onError();
    }
}
