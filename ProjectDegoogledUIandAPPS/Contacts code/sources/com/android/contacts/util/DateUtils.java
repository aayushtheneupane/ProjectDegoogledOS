package com.android.contacts.util;

import android.content.Context;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    private static final SimpleDateFormat[] DATE_FORMATS = {CommonDateUtils.FULL_DATE_FORMAT, CommonDateUtils.DATE_AND_TIME_FORMAT, new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US), new SimpleDateFormat("yyyyMMdd'T'HHmmssSSS'Z'", Locale.US), new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'", Locale.US), new SimpleDateFormat("yyyyMMdd'T'HHmm'Z'", Locale.US)};
    public static final String NO_YEAR_DATE_FEB29TH = "--02-29";
    public static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("UTC");

    static {
        for (SimpleDateFormat simpleDateFormat : DATE_FORMATS) {
            simpleDateFormat.setLenient(true);
            simpleDateFormat.setTimeZone(UTC_TIMEZONE);
        }
        CommonDateUtils.NO_YEAR_DATE_FORMAT.setTimeZone(UTC_TIMEZONE);
    }

    public static Calendar parseDate(String str, boolean z) {
        Date parse;
        ParsePosition parsePosition = new ParsePosition(0);
        if (!z) {
            if (NO_YEAR_DATE_FEB29TH.equals(str)) {
                return getUtcDate(0, 1, 29);
            }
            synchronized (CommonDateUtils.NO_YEAR_DATE_FORMAT) {
                parse = CommonDateUtils.NO_YEAR_DATE_FORMAT.parse(str, parsePosition);
            }
            if (parsePosition.getIndex() == str.length()) {
                return getUtcDate(parse, true);
            }
        }
        int i = 0;
        while (true) {
            SimpleDateFormat[] simpleDateFormatArr = DATE_FORMATS;
            if (i >= simpleDateFormatArr.length) {
                return null;
            }
            SimpleDateFormat simpleDateFormat = simpleDateFormatArr[i];
            synchronized (simpleDateFormat) {
                parsePosition.setIndex(0);
                Date parse2 = simpleDateFormat.parse(str, parsePosition);
                if (parsePosition.getIndex() == str.length()) {
                    Calendar utcDate = getUtcDate(parse2, false);
                    return utcDate;
                }
            }
            i++;
        }
    }

    private static final Calendar getUtcDate(Date date, boolean z) {
        Calendar instance = Calendar.getInstance(UTC_TIMEZONE, Locale.US);
        instance.setTime(date);
        if (z) {
            instance.set(1, 0);
        }
        return instance;
    }

    private static final Calendar getUtcDate(int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance(UTC_TIMEZONE, Locale.US);
        instance.clear();
        instance.set(1, i);
        instance.set(2, i2);
        instance.set(5, i3);
        return instance;
    }

    public static boolean isYearSet(Calendar calendar) {
        return calendar.get(1) > 1;
    }

    public static String formatDate(Context context, String str) {
        return formatDate(context, str, true);
    }

    public static String formatDate(Context context, String str, boolean z) {
        Calendar parseDate;
        DateFormat dateFormat;
        String format;
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0 || (parseDate = parseDate(trim, false)) == null) {
            return trim;
        }
        if (!isYearSet(parseDate)) {
            dateFormat = getLocalizedDateFormatWithoutYear(context);
        } else if (z) {
            dateFormat = android.text.format.DateFormat.getLongDateFormat(context);
        } else {
            dateFormat = android.text.format.DateFormat.getDateFormat(context);
        }
        synchronized (dateFormat) {
            dateFormat.setTimeZone(UTC_TIMEZONE);
            format = dateFormat.format(parseDate.getTime());
        }
        return format;
    }

    public static boolean isMonthBeforeDay(Context context) {
        char[] dateFormatOrder = android.text.format.DateFormat.getDateFormatOrder(context);
        int i = 0;
        while (i < dateFormatOrder.length && dateFormatOrder[i] != 'd') {
            if (dateFormatOrder[i] == 'M') {
                return true;
            }
            i++;
        }
        return false;
    }

    public static DateFormat getLocalizedDateFormatWithoutYear(Context context) {
        String pattern = ((SimpleDateFormat) SimpleDateFormat.getDateInstance(1)).toPattern();
        try {
            return new SimpleDateFormat(pattern.replaceAll(pattern.contains("de") ? "[^Mm]*[Yy]+[^Mm]*" : "[^DdMm]*[Yy]+[^DdMm]*", ""));
        } catch (IllegalArgumentException unused) {
            return new SimpleDateFormat(isMonthBeforeDay(context) ? "MMMM dd" : "dd MMMM");
        }
    }

    public static Date getNextAnnualDate(Calendar calendar) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        boolean z = false;
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        boolean isYearSet = isYearSet(calendar);
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        if (i2 == 1 && i3 == 29) {
            z = true;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if (!isYearSet) {
            i = instance.get(1);
        }
        gregorianCalendar.set(i, i2, i3);
        if (!isYearSet) {
            int i4 = instance.get(1);
            if (gregorianCalendar.before(instance) || (z && !gregorianCalendar.isLeapYear(i4))) {
                do {
                    i4++;
                    if (!z || gregorianCalendar.isLeapYear(i4)) {
                        gregorianCalendar.set(i4, i2, i3);
                    }
                    i4++;
                    break;
                } while (gregorianCalendar.isLeapYear(i4));
                gregorianCalendar.set(i4, i2, i3);
            }
        }
        return gregorianCalendar.getTime();
    }
}
