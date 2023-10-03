package com.android.systemui.pip.phone;

import android.os.Handler;
import android.os.HandlerThread;

public final class ForegroundThread extends HandlerThread {
    private static Handler sHandler;
    private static ForegroundThread sInstance;

    private ForegroundThread() {
        super("recents.fg");
    }

    private static void ensureThreadLocked() {
        if (sInstance == null) {
            sInstance = new ForegroundThread();
            sInstance.start();
            sHandler = new Handler(sInstance.getLooper());
        }
    }

    public static ForegroundThread get() {
        ForegroundThread foregroundThread;
        synchronized (ForegroundThread.class) {
            ensureThreadLocked();
            foregroundThread = sInstance;
        }
        return foregroundThread;
    }
}
