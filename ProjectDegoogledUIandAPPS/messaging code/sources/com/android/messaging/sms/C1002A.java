package com.android.messaging.sms;

import android.content.res.Resources;
import com.android.messaging.C0967f;
import com.android.messaging.R;
import com.android.messaging.datamodel.action.SyncMessagesAction;
import com.android.messaging.util.C1430e;
import com.android.messaging.util.C1449g;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.sms.A */
public class C1002A {

    /* renamed from: cF */
    private static final C1030z f1456cF = new C1030z(1, 109);

    /* renamed from: dF */
    private static final Pattern f1457dF = Pattern.compile("([1-9]+\\d*)(w|m|y)");

    /* renamed from: Mi */
    public static C1030z m2323Mi() {
        C1449g.get().getString("bugle_sms_storage_purging_message_retaining_duration", "1m");
        Matcher matcher = f1457dF.matcher("1m");
        try {
            if (matcher.matches()) {
                return new C1030z(Integer.parseInt(matcher.group(1)), matcher.group(2).charAt(0));
            }
        } catch (NumberFormatException unused) {
        }
        C1430e.m3622e("MessagingApp", "SmsAutoDelete: invalid duration " + "1m");
        return f1456cF;
    }

    /* renamed from: a */
    public static long m2324a(C1030z zVar) {
        long j;
        long j2;
        int i = zVar.f1557bF;
        if (i == 109) {
            j = (long) zVar.mCount;
            j2 = 2592000000L;
        } else if (i == 119) {
            j = (long) zVar.mCount;
            j2 = 604800000;
        } else if (i != 121) {
            return -1;
        } else {
            j = (long) zVar.mCount;
            j2 = 31536000000L;
        }
        return j * j2;
    }

    /* renamed from: b */
    public static String m2325b(C1030z zVar) {
        Resources resources = C0967f.get().getApplicationContext().getResources();
        int i = zVar.f1557bF;
        if (i == 109) {
            int i2 = zVar.mCount;
            return resources.getQuantityString(R.plurals.month_count, i2, new Object[]{Integer.valueOf(i2)});
        } else if (i == 119) {
            int i3 = zVar.mCount;
            return resources.getQuantityString(R.plurals.week_count, i3, new Object[]{Integer.valueOf(i3)});
        } else if (i == 121) {
            int i4 = zVar.mCount;
            return resources.getQuantityString(R.plurals.year_count, i4, new Object[]{Integer.valueOf(i4)});
        } else {
            StringBuilder Pa = C0632a.m1011Pa("SmsAutoDelete: invalid duration unit ");
            Pa.append(zVar.f1557bF);
            throw new IllegalArgumentException(Pa.toString());
        }
    }

    /* renamed from: b */
    public static void m2326b(int i, long j) {
        int i2;
        if (i == 0) {
            i2 = C1029y.m2405Fi();
        } else if (i != 1) {
            C1430e.m3622e("MessagingApp", "SmsStorageStatusManager: invalid action " + i);
            i2 = 0;
        } else {
            i2 = C1029y.m2452y(System.currentTimeMillis() - j);
        }
        if (i2 > 0) {
            SyncMessagesAction.sync();
        }
    }
}
