package com.android.messaging.sms;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.support.p016v4.media.session.C0107q;
import com.android.messaging.util.C1430e;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.sms.v */
public final class C1026v implements Telephony.ThreadsColumns {

    /* renamed from: OE */
    private static final String[] f1544OE = {"_id"};

    /* renamed from: QE */
    private static final Uri f1545QE = Uri.parse("content://mms-sms/threadID");

    static {
        Uri.withAppendedPath(Telephony.MmsSms.CONTENT_URI, "conversations");
    }

    public static long getOrCreateThreadId(Context context, String str) {
        HashSet hashSet = new HashSet();
        hashSet.add(str);
        return getOrCreateThreadId(context, (Set) hashSet);
    }

    public static long getOrCreateThreadId(Context context, Set set) {
        Uri.Builder buildUpon = f1545QE.buildUpon();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (C1027w.isEmailAddress(str)) {
                Matcher matcher = C1027w.NAME_ADDR_EMAIL_PATTERN.matcher(str);
                if (matcher.matches()) {
                    str = matcher.group(2);
                }
            }
            buildUpon.appendQueryParameter("recipient", str);
        }
        Cursor query = C0107q.query(context.getContentResolver(), buildUpon.build(), f1544OE, (String) null, (String[]) null, (String) null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    return query.getLong(0);
                }
                C1430e.m3622e("MessagingAppDataModel", "getOrCreateThreadId returned no rows!");
                query.close();
            } finally {
                query.close();
            }
        }
        StringBuilder Pa = C0632a.m1011Pa("getOrCreateThreadId failed with ");
        Pa.append(C1430e.m3633xa(set.toString()));
        C1430e.m3622e("MessagingAppDataModel", Pa.toString());
        throw new IllegalArgumentException("Unable to find or allocate a thread ID.");
    }
}
