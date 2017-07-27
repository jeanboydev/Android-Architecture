package com.jeanboy.base.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by jeanboy on 2017/5/17.
 */

public class DeviceIdUtil {

    private final static String WEATHER_APP_DEVICE_ID_KEY = "app_device_id_key";

    public static String getDeviceId(Context context) {
        String deviceID = SharedPreferencesUtil.getString(context, WEATHER_APP_DEVICE_ID_KEY, null);
        if (TextUtils.isEmpty(deviceID)) {
            String uuid = null;
            try {
                final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                // Use the Android ID unless it's broken, in which case fallback on deviceId,
                // unless it's not available, then fallback on a random number which we store
                // to a prefs file
                if (!"9774d56d682e549c".equals(androidId)) {
                    uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8")).toString();
                } else {
                    final String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                    uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")).toString() : UUID.randomUUID().toString();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(uuid)) {
                uuid = UUID.randomUUID().toString();
            }
            SharedPreferencesUtil.putString(context, WEATHER_APP_DEVICE_ID_KEY, uuid);
            return uuid;
        } else {
            return deviceID;
        }
    }
}
