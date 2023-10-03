package com.android.messaging.util;

import android.os.SystemClock;
import android.util.Log;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: com.android.messaging.util.da */
public class C1429da {
    private final String mName;
    private final String mTag;

    /* renamed from: tK */
    private final long f2239tK;

    /* renamed from: uK */
    private long f2240uK;

    public C1429da(String str, String str2, long j) {
        this.mTag = str;
        this.mName = str2;
        this.f2239tK = j;
    }

    /* renamed from: Pj */
    public void mo8056Pj() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f2240uK;
        String format = String.format("Used %dms for %s", new Object[]{Long.valueOf(elapsedRealtime), this.mName});
        C1430e.m3613a(3, this.mTag, format);
        long j = this.f2239tK;
        if (j != -1 && elapsedRealtime > j) {
            C1430e.m3630w(this.mTag, format);
        } else if (Log.isLoggable(this.mTag, 2)) {
            C1430e.m3628v(this.mTag, format);
        }
    }

    public void start() {
        this.f2240uK = SystemClock.elapsedRealtime();
        if (Log.isLoggable(this.mTag, 2)) {
            String str = this.mTag;
            StringBuilder Pa = C0632a.m1011Pa("Timer start for ");
            Pa.append(this.mName);
            C1430e.m3628v(str, Pa.toString());
        }
    }
}
