package com.android.messaging.util;

import com.android.messaging.C0967f;

/* renamed from: com.android.messaging.util.g */
public class C1449g {
    public static C1449g get() {
        return C0967f.get().mo6646Id();
    }

    /* renamed from: jb */
    private void m3722jb(String str) {
        C1424b.m3592ia(str.startsWith("bugle_"));
    }

    public boolean getBoolean(String str, boolean z) {
        m3722jb(str);
        return z;
    }

    public float getFloat(String str, float f) {
        m3722jb(str);
        return f;
    }

    public int getInt(String str, int i) {
        m3722jb(str);
        return i;
    }

    public long getLong(String str, long j) {
        m3722jb(str);
        return j;
    }

    public String getString(String str, String str2) {
        m3722jb(str);
        return str2;
    }

    /* renamed from: i */
    public void mo8172i(Runnable runnable) {
    }
}
