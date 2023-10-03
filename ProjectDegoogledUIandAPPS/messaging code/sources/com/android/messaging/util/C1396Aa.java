package com.android.messaging.util;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.Locale;

/* renamed from: com.android.messaging.util.Aa */
public final class C1396Aa {
    private static C1396Aa sInstance;
    private static final Object sLock = new Object();

    /* renamed from: eL */
    private final String f2205eL;

    private C1396Aa(Context context) {
        int i;
        try {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            C1424b.fail("couldn't get package info " + e);
            i = -1;
        }
        int i2 = i / 1000;
        this.f2205eL = String.format(Locale.US, "%d.%d.%03d", new Object[]{Integer.valueOf(i2 / 10000), Integer.valueOf((i2 / 1000) % 10), Integer.valueOf(i2 % 1000)});
    }

    public static C1396Aa getInstance(Context context) {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new C1396Aa(context);
            }
        }
        return sInstance;
    }

    public String getSimpleName() {
        return this.f2205eL;
    }
}
