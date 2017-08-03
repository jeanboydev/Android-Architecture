package com.jeanboy.base.wrapper;

/**
 * Created by jeanboy on 2017/8/2.
 * <p>
 * 用于初始化butterknife
 */

public interface BindWrapper {

    /**
     * activity中：onSetContentView()后调用
     * fragment中：onFragmentViewCreated()后调用
     */
    void onBind(Object target);

    /**
     * activity中：onDestroy()后调用
     * fragment中：onDestroyView()后调用
     */
    void onUnbind();
}
