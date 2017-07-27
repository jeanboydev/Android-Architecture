package com.jeanboy.base.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by jeanboy on 2017/5/23.
 */

public class SnackbarUtil {

    public static void show(View view, String message, String action, View.OnClickListener listener) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(action, listener).show();
    }

    public static void show(View view, int messageId, int actionId, View.OnClickListener listener) {
        Snackbar.make(view, messageId, Snackbar.LENGTH_LONG).setAction(actionId, listener).show();
    }

}
