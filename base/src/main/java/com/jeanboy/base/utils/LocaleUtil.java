package com.jeanboy.base.utils;

import java.util.Locale;

/**
 * Created by jeanboy on 2017/5/23.
 */

public class LocaleUtil {

    /**
     * 获取系统上的语言列表
     *
     * @return
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    public static String getCurrentLanguage() {
        Locale locale = Locale.getDefault();
        if (locale.getLanguage().equals(Locale.CHINA.getLanguage())) {
            return locale.getLanguage() + "-" + locale.getCountry();
        }
        return locale.getLanguage();
    }

    public static String getCurrentCountry() {
        return Locale.getDefault().getCountry();
    }
}
