package com.jeanboy.base.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;

/**
 * Created by jeanboy on 2017/5/26.
 */

public class ActivityUtil {

    /**
     * startActivity兼容处理
     *
     * @param intent
     * @param pairs
     */
    @SafeVarargs
    public static void startAwesomeActivity(Activity activity, Intent intent, Pair<View, String>... pairs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
                    pairs).toBundle());
        } else {
            activity.startActivity(intent);
        }
    }
}
