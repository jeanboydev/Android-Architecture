package com.jeanboy.base.utils;

import android.text.TextUtils;

import java.util.Random;

/**
 * Created by jeanboy on 2016/7/8.
 */
public class RandomUtil {

    public RandomUtil() {
        throw new AssertionError();
    }

    public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERS = "0123456789";
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 获取大写，小写字母，数字混合字符串
     *
     * @param length length
     * @return RandomUtils
     */
    public static String getRandomNumbersAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }

    /**
     * 获取数字字符串
     *
     * @param length length
     * @return RandomUtils
     */
    public static String getRandomNumbers(int length) {
        return getRandom(NUMBERS, length);
    }

    /**
     * 获取大写，小写字母混合字符串
     *
     * @param length length
     * @return RandomUtils
     */
    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    /**
     * 获取大写字母字符串
     *
     * @param length length
     * @return CapitalLetters
     */
    public static String getRandomCapitalLetters(int length) {
        return getRandom(CAPITAL_LETTERS, length);
    }

    /**
     * 获取小写字母字符串
     *
     * @param length length
     * @return get a fixed-length random string, its a mixture of lowercase letters
     */
    public static String getRandomLowerCaseLetters(int length) {
        return getRandom(LOWER_CASE_LETTERS, length);
    }

    /**
     * 获取指定源字符串
     *
     * @param source source
     * @param length length
     * @return get a fixed-length random string, its a mixture of chars in source
     */
    public static String getRandom(String source, int length) {
        return TextUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
    }

    /**
     * 获取指定数组源字符串
     *
     * @param sourceChar sourceChar
     * @param length     length
     * @return get a fixed-length random string, its a mixture of chars in sourceChar
     */
    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }

        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    /**
     * (0, max]
     *
     * @param max 接受的数值
     * @return 返回一个随机的数值
     */
    public static int getRandom(int max) {
        return getRandom(0, max);
    }


    /**
     * (min, max]
     *
     * @param min 最小
     * @param max 最大
     * @return 返回一个范围的数值
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }

}
