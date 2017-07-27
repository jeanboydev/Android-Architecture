package com.jeanboy.base.utils;

import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jeanboy on 2016/7/8.
 */
public class StringUtil {

    public StringUtil() {
        throw new AssertionError();
    }

    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static boolean regExCompile(String str, String regEx) {
        boolean flag = false;
        if (str != null && regEx != null) {
            Pattern pat = Pattern.compile(regEx);
            Matcher mat = pat.matcher(str);
            flag = mat.matches();
        }
        return flag;
    }

    public static String analyseMoney(long money) {
        if (money >= 100) {
            StringBuilder builder = new StringBuilder();
            builder.append(money + "").insert((money + "").length() - 2, ".");
            return builder.toString();
        } else if (money < 100 && money >= 10) {
            return "0." + money;
        } else {
            return "0.0" + money;
        }
    }

    public static String analyseCount(int count) {
        if (count >= 10000) {
            return (double) (Math.round(count * 100 / 10000) / 100.0) + "w";
        } else if (count >= 1000 && count < 10000) {
            return (double) (Math.round(count * 100 / 1000) / 100.0) + "k";
        } else {
            return count + "";
        }
    }

    private static final int SIZE_K = 1024;
    private static final int SIZE_M = 1024 * SIZE_K;
    private static final int SIZE_G = 1024 * SIZE_M;


    public static String analyseSize(long byteSize) {
        if (byteSize > SIZE_G) {
            return (Math.round(byteSize * 100 / SIZE_G) / 100.0) + "G";
        } else if (byteSize > SIZE_M) {
            return (Math.round(byteSize * 100 / SIZE_M) / 100.0) + "M";
        } else {
            return (Math.round(byteSize * 100 / SIZE_K) / 100.0) + "K";
        }
    }

    /**
     * double 保留两位小数
     *
     * @param d
     * @return
     */
    public static String formatDecimal2(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setRoundingMode(RoundingMode.HALF_UP);
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(2);
        return nf.format(d);
    }

}
