package com.android.messaging.util;

import android.os.Handler;
import android.os.Looper;

/* renamed from: com.android.messaging.util.va */
public class C1480va {
    private static final Handler sHandler = new Handler(Looper.getMainLooper());

    public static Handler getMainThreadHandler() {
        return sHandler;
    }
}
