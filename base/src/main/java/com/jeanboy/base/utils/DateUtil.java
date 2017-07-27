package com.jeanboy.base.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by jeanboy on 2017/5/19.
 */

public class DateUtil {

    /**
     * https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
     * Letter	Date or Time Component	Presentation	Examples
     * G	Era designator	Text	AD
     * y	Year	Year	1996; 96
     * Y	Week year	Year	2009; 09
     * M	Month in year	Month	July; Jul; 07
     * w	Week in year	Number	27
     * W	Week in month	Number	2
     * D	Day in year	Number	189
     * d	Day in month	Number	10
     * F	Day of week in month	Number	2
     * E	Day name in week	Text	Tuesday; Tue
     * u	Day number of week (1 = Monday, ..., 7 = Sunday)	Number	1
     * a	Am/pm marker	Text	PM
     * H	Hour in day (0-23)	Number	0
     * k	Hour in day (1-24)	Number	24
     * K	Hour in am/pm (0-11)	Number	0
     * h	Hour in am/pm (1-12)	Number	12
     * m	Minute in hour	Number	30
     * s	Second in minute	Number	55
     * S	Millisecond	Number	978
     * z	Time zone	General time zone	Pacific Standard Time; PST; GMT-08:00
     * Z	Time zone	RFC 822 time zone	-0800
     * X	Time zone	ISO 8601 time zone	-08; -0800; -08:00
     */

    public static final String FORMAT_DATE_24_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_12_FULL = "yyyy-MM-dd hh:mm:ss";//2016-11-28 11:53:00
    public static final String FORMAT_DATE_12_MARKER = "a";//上午/下午

    public static final String FORMAT_TIME_24_FULL = "HH:mm:ss";
    public static final String FORMAT_TIME_12_FULL = "hh:mm:ss a";
    public static final String FORMAT_TIME_24_SIMPLE = "HH:mm";
    public static final String FORMAT_TIME_12_SIMPLE = "hh:mm a";

    public static final String FORMAT_HOURS_24_HOUR = "H";//1-24
    public static final String FORMAT_HOURS_12_HOUR = "h";//1-12
    public static final String FORMAT_WEEK = "E";//星期一/ Monday
    public static final String FORMAT_SHARE = "E.M.dd";// Monday.Jul.18
    public static final String FORMAT_DATE_SIMPLE = "MM/dd";

    public static final String FORMAT_ACC_TURBO = "MM/dd/yyyy h a";//5/16/2017 12 PM
    public static final String FORMAT_ACC_TURBO_SUN = "MM/dd/yyyy h:mm a";//5/16/2017 12:00 PM
    public static final String FORMAT_ACC_TURBO_DATE = "MM/dd/yyyy";//5/16/2017
    private static String nowTime;

    public static String dateFormat(long timestamp, String format, String timeZoneId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        if (timeZoneId != null) {
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        }else {
            dateFormat.setTimeZone(TimeZone.getDefault());
        }
        return dateFormat.format(calendar.getTime());
    }

    public static Date stringToDate(String dateStr, String format, String timeZoneId) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        if (timeZoneId != null) {
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZoneId));
        }else {
            dateFormat.setTimeZone(TimeZone.getDefault());
        }
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getNowTime() {
        return nowTime;
    }

    public static boolean is24HourFormat(Context context) {
        return DateFormat.is24HourFormat(context);
    }

    public static String dateFormatUseSystem(Context context, long timestamp, String timeZoneId) {
        String format = FORMAT_DATE_12_FULL + " " + FORMAT_DATE_12_MARKER;
        if (is24HourFormat(context)) {
            format = FORMAT_DATE_24_FULL;
        }
        return dateFormat(timestamp, format, timeZoneId);
    }

    public static String timeFormatUseSystem(Context context, long timestamp, String timeZoneId) {
        String format = FORMAT_TIME_12_SIMPLE;
        if (is24HourFormat(context)) {
            format = FORMAT_TIME_24_SIMPLE;
        }
        return dateFormat(timestamp, format, timeZoneId);
    }
}
