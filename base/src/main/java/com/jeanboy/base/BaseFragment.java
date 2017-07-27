package com.jeanboy.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jeanboy on 2017/7/27.
 */

public abstract class BaseFragment extends Fragment {

    public String TAG = this.getClass().getSimpleName();

    private Toolbar toolbar;
//    private Unbinder unbinder;

    protected abstract int getLayoutId();

    protected abstract void setupArguments(Bundle args);

    protected abstract void setupView(View view, Bundle savedInstanceState);

    protected abstract void initData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        onInject();

        if (getArguments() != null) {
            setupArguments(getArguments());
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        unbinder = ButterKnife.bind(this, view);
        setupToolbar(view);
        setupView(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
//        if (unbinder != null) {
//            unbinder.unbind();
//        }
        super.onDestroyView();
    }

    /**
     * 初始化Toolbar
     */
    private void setupToolbar(View view) {
        if (toolbar == null) {
            toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle(null);
                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            }
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
