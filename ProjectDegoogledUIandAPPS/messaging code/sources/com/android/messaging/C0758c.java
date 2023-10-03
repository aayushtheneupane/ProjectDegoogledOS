package com.android.messaging;

/* renamed from: com.android.messaging.c */
class C0758c implements Runnable {

    /* renamed from: Zw */
    final /* synthetic */ Thread f979Zw;

    /* renamed from: _w */
    final /* synthetic */ Throwable f980_w;
    final /* synthetic */ BugleApplication this$0;

    C0758c(BugleApplication bugleApplication, Thread thread, Throwable th) {
        this.this$0 = bugleApplication;
        this.f979Zw = thread;
        this.f980_w = th;
    }

    public void run() {
        this.this$0.f977Jb.uncaughtException(this.f979Zw, this.f980_w);
    }
}
