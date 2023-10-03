package com.android.messaging.util;

import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.format.Time;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* renamed from: com.android.messaging.util.y */
public class C1485y {
    public static final int FORCE_12_HOUR = 64;
    public static final int FORCE_24_HOUR = 128;

    /* renamed from: VJ */
    private static Time f2353VJ;

    /* renamed from: B */
    public static CharSequence m3835B(long j) {
        return m3842a(j, true, false);
    }

    /* renamed from: C */
    public static CharSequence m3836C(long j) {
        return m3842a(j, true, true);
    }

    /* renamed from: D */
    public static CharSequence m3837D(long j) {
        Context context = getContext();
        return m3840a(j, context.getResources().getConfiguration().locale, false, DateFormat.is24HourFormat(context) ? 128 : 64);
    }

    /* renamed from: E */
    public static CharSequence m3838E(long j) {
        return m3842a(j, false, false);
    }

    /* renamed from: a */
    public static CharSequence m3841a(long j, boolean z) {
        return m3842a(j, z, true);
    }

    /* renamed from: c */
    private static synchronized long m3843c(long j, long j2) {
        long abs;
        synchronized (C1485y.class) {
            if (f2353VJ == null) {
                f2353VJ = new Time();
            }
            f2353VJ.set(j);
            int julianDay = Time.getJulianDay(j, f2353VJ.gmtoff);
            f2353VJ.set(j2);
            abs = (long) Math.abs(Time.getJulianDay(j2, f2353VJ.gmtoff) - julianDay);
        }
        return abs;
    }

    private static Context getContext() {
        return C0967f.get().getApplicationContext();
    }

    public static CharSequence getTimestamp(long j, long j2, boolean z, Locale locale, int i, boolean z2) {
        long j3 = j2 - j;
        if (!z2 && j3 < 60000) {
            return getContext().getResources().getText(z ? R.string.posted_just_now : R.string.posted_now);
        } else if (!z2 && j3 < 3600000) {
            long j4 = j3 / 60000;
            return String.format(getContext().getResources().getQuantityString(R.plurals.num_minutes_ago, (int) j4), new Object[]{Long.valueOf(j4)});
        } else if (m3843c(j, j2) == 0) {
            return DateUtils.formatDateTime(getContext(), j, i | 1);
        } else {
            if (j3 < 604800000) {
                Context context = getContext();
                if (z) {
                    return DateUtils.formatDateTime(context, j, 32770 | i);
                }
                if (locale.equals(Locale.US)) {
                    return m3839a(j, i, "EEE HH:mm", "EEE h:mmaa");
                }
                return DateUtils.formatDateTime(context, j, 32771 | i);
            } else if (j3 >= 31449600000L) {
                return m3840a(j, locale, z, i);
            } else {
                Context context2 = getContext();
                if (z) {
                    return DateUtils.formatDateTime(context2, j, 65560 | i);
                }
                if (locale.equals(Locale.US)) {
                    return m3839a(j, i, "MMM d, HH:mm", "MMM d, h:mmaa");
                }
                return DateUtils.formatDateTime(context2, j, 65561 | i);
            }
        }
    }

    /* renamed from: a */
    private static CharSequence m3842a(long j, boolean z, boolean z2) {
        Context context = getContext();
        return getTimestamp(j, System.currentTimeMillis(), z, context.getResources().getConfiguration().locale, DateFormat.is24HourFormat(context) ? 128 : 64, z2);
    }

    /* renamed from: a */
    private static CharSequence m3839a(long j, int i, String str, String str2) {
        SimpleDateFormat simpleDateFormat;
        if ((i & 128) == 128) {
            simpleDateFormat = new SimpleDateFormat(str);
        } else {
            simpleDateFormat = new SimpleDateFormat(str2);
        }
        return simpleDateFormat.format(new Date(j));
    }

    /* renamed from: a */
    private static CharSequence m3840a(long j, Locale locale, boolean z, int i) {
        Context context = getContext();
        if (z) {
            if (locale.equals(Locale.US)) {
                return m3839a(j, i, "M/d/yy", "M/d/yy");
            }
            return DateUtils.formatDateTime(context, j, 131092);
        } else if (locale.equals(Locale.US)) {
            return m3839a(j, i, "M/d/yy, HH:mm", "M/d/yy, h:mmaa");
        } else {
            return DateUtils.formatDateTime(context, j, 131093 | i);
        }
    }
}
