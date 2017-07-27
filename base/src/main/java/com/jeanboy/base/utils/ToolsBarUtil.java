package com.jeanboy.base.utils;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jeanboy.base.R;

/**
 * Created by jeanboy on 2017/7/27.
 */

public class ToolsBarUtil {

    /**
     * 设置Toolbar的标题
     *
     * @param toolbar
     * @param resId
     */
    public static void setToolBarTitle(Toolbar toolbar, int resId) {
        if (toolbar == null) return;
        TextView titleView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        if (titleView == null) return;
        titleView.setText(resId);
    }

    /**
     * 设置Toolbar的标题
     *
     * @param toolbar
     * @param title
     */
    public static void setToolBarTitle(Toolbar toolbar, CharSequence title) {
        if (toolbar == null) return;
        TextView titleView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        if (titleView == null) return;
        titleView.setText(title);
    }

    /**
     * 设置Toolbar左边按钮
     *
     * @param toolbar
     * @param resId
     * @param descId
     * @param listener
     */
    public static void setToolbarNavigation(Toolbar toolbar, int resId, int descId, View.OnClickListener listener) {
        if (toolbar == null) return;
        toolbar.setNavigationIcon(resId);
        toolbar.setNavigationContentDescription(descId);
        toolbar.setNavigationOnClickListener(listener);
    }

    /**
     * 设置状态栏有菜单
     *
     * @param fragment
     */
    public static void setToolBarHasMenu(Fragment fragment) {
        if (fragment == null) return;
        fragment.setHasOptionsMenu(true);
    }

    /**
     * 向Toolbar添加menu，一般fragment使用
     *
     * @param toolbar
     * @param layout
     * @param itemClickListener
     */
    public static void addMenu(Toolbar toolbar, int layout, Toolbar.OnMenuItemClickListener itemClickListener) {
        if (toolbar == null) return;
        toolbar.inflateMenu(layout);
        toolbar.setOnMenuItemClickListener(itemClickListener);
    }

    /**
     * 设置Toolbar左上角箭头可用
     *
     * @param activity
     */
    public static void setToolbarHomeAsUp(AppCompatActivity activity) {
        if (activity == null) return;
        if (activity.getSupportActionBar() == null) return;
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 向上需要全部滚出屏幕时设置topMargin代替fitsSystemWindows="true"
     *
     * @param toolbar
     */
    public static void setStatusBarFits(Toolbar toolbar) {
        if (toolbar == null) return;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (toolbar.getLayoutParams() instanceof AppBarLayout.LayoutParams) {
                AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
                params.topMargin = StatusBarUtil.getStatusBarHeight(toolbar.getContext());
                toolbar.setLayoutParams(params);

                hideNavigationBar((Activity) toolbar.getContext());
            }
        }
    }

    /**
     * 隐藏虚拟按键
     *
     * @param activity
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static void hideNavigationBar(Activity activity) {
        if (NavigationBarUtil.hasNavigationBar(activity)) {
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE//
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//全屏，显示状态栏
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION//隐藏虚拟按键
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * 设置状态栏全透明
     *
     * @param activity
     */
    public static void setStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarUtil.setTransparent(activity);
        }
    }

    /**
     * 设置状态栏半透明
     *
     * @param activity
     */
    public static void setStatusBarTranslucent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarUtil.setTranslucent(activity);
        }
    }

    /**
     * 设置状态栏背景颜色
     *
     * @param activity
     * @param color
     */
    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarUtil.setColor(activity, color);
        }
    }

    /**
     * 设置状态栏为高亮模式
     *
     * @param activity
     */
    public static void setStatusBarLight(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarColor(activity, ContextCompat.getColor(activity, android.R.color.white));//设置背景颜色为亮色
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置文本颜色为暗色
        } else {
            setStatusBarTranslucent(activity);//设置背景颜色为半透明
        }
    }

    /**
     * 设置状态栏为默认
     *
     * @param activity
     */
    public static void setStatusBarDefault(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarUtil.setColor(activity, ContextCompat.getColor(activity, R.color.colorPrimaryDark));
        }
    }

    private static boolean isSetImmersive = false;

    /**
     * 设置状态栏为沉浸模式
     *
     * @param activity
     * @param isImmersive
     */
    public static void setStatusBarImmersive(Activity activity, boolean isImmersive) {
        isSetImmersive = isImmersive;
        if (isImmersive && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarUtil.setImmersive(activity, true);
        }
    }

    /**
     * 设置状态栏沉浸模式时自动隐藏
     * 在 onWindowFocusChanged() 中调用
     *
     * @param activity
     * @param hasFocus
     */
    public static void setStatusBarImmersiveWindowFocusChanged(Activity activity, boolean hasFocus) {
        if (isSetImmersive && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            StatusBarUtil.setImmersive(activity, hasFocus);
        }
    }
}
