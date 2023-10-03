package com.android.messaging.datamodel.p038b;

import java.util.concurrent.ThreadFactory;

/* renamed from: com.android.messaging.datamodel.b.y */
class C0885y implements ThreadFactory {
    C0885y() {
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setPriority(1);
        return thread;
    }
}
