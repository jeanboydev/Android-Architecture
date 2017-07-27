package com.jeanboy.base.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

/**
 * created by jeanboy on 2017/7/27 12:07
 */
public class ToastUtil {
    private ToastUtil() {
        throw new AssertionError();
    }

    public static void toast(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    public static void toast(Context context, CharSequence message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static void toast(Context context, CharSequence message) {
        toast(context, message, Toast.LENGTH_SHORT);
    }

    public static void toast(Context context, int resId) {
        toast(context, resId, Toast.LENGTH_SHORT);
    }

    /**
     * 延时 Toast
     * @param context
     * @param message
     * @param delayMillis
     */
    public static void toastDelay(Context context, CharSequence message, long delayMillis) {
        final Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                toast.cancel();
            }
        }, delayMillis);
    }

    public static void toastDelay(Context context, int resId, long delayMillis) {
        toastDelay(context, context.getResources().getString(resId), delayMillis);
    }
}
