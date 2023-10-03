package com.android.messaging;

import android.os.Process;

/* renamed from: com.android.messaging.g */
class C0968g extends Thread {
    final /* synthetic */ C0969h this$0;

    C0968g(C0969h hVar) {
        this.this$0 = hVar;
    }

    public void run() {
        Process.setThreadPriority(10);
        this.this$0.mApplication.mo5855a((C0967f) this.this$0);
    }
}
