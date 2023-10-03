package com.android.messaging.util;

import android.os.Process;

/* renamed from: com.android.messaging.util.Ba */
public class C1398Ba {
    public static final String EXTRA_CALLING_PID = "pid";

    /* renamed from: fL */
    private final String f2206fL;

    /* renamed from: gL */
    private final int f2207gL;
    private final Object mLock = new Object();

    public C1398Ba(String str) {
        this.f2206fL = str;
        this.f2207gL = Process.myPid();
    }
}
