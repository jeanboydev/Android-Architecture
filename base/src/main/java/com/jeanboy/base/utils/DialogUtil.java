package com.jeanboy.base.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by jeanboy on 2017/5/17.
 */

public class DialogUtil {

    private static AlertDialog.Builder getDialogBuilder(Context context) {
        return new AlertDialog.Builder(context);
    }

    private static void showAlert(AlertDialog.Builder builder, final Callback callback) {
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.proceed();
            }
        }).show();
    }

    public static void showAlert(Context context, int messageId, final Callback callback) {
        showAlert(getDialogBuilder(context).setMessage(messageId), callback);
    }

    public static void showAlert(Context context, String message, final Callback callback) {
        showAlert(getDialogBuilder(context).setMessage(message), callback);
    }

    private static void showSelect(AlertDialog.Builder builder, CharSequence[] items, final OnSelectCallBack callback) {
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callback == null) return;
                callback.onSelect(dialog, which);
            }
        });
        builder.show();
    }

    public static void showSelectItems(Context context, String[] items, final OnSelectCallBack callback) {
        showSelect(getDialogBuilder(context), items, callback);
    }

    public static void showSelectItems(Context context, int itemsId, final OnSelectCallBack callback) {
        showSelect(getDialogBuilder(context), context.getResources().getTextArray(itemsId), callback);
    }

    private static void showSingleChoice(AlertDialog.Builder builder, CharSequence[] items, int defaultItem, final
    OnSelectCallBack callback) {
        builder.setSingleChoiceItems(items, defaultItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (callback == null) return;
                callback.onSelect(dialog, which);
            }
        });
        builder.show();
    }

    public static void showSingleChoiceItems(Context context, String[] items, int defaultItem, final OnSelectCallBack callback) {
        showSingleChoice(getDialogBuilder(context), items, defaultItem, callback);
    }

    public static void showSingleChoiceItems(Context context, int itemsId, int defaultItem, final OnSelectCallBack callback) {
        showSingleChoice(getDialogBuilder(context), context.getResources().getTextArray(itemsId), defaultItem, callback);
    }

    public interface Callback {

        void proceed();

        void cancel();
    }

    public interface OnSelectCallBack {

        void onSelect(DialogInterface dialog, int position);

    }
}
