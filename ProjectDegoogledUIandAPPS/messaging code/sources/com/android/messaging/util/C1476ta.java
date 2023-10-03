package com.android.messaging.util;

import android.os.AsyncTask;

/* renamed from: com.android.messaging.util.ta */
class C1476ta implements Runnable {
    final /* synthetic */ C1478ua this$0;

    C1476ta(C1478ua uaVar) {
        this.this$0 = uaVar;
    }

    public void run() {
        if (this.this$0.getStatus() == AsyncTask.Status.RUNNING) {
            C1430e.m3630w("MessagingApp", String.format("%s timed out and is canceled", new Object[]{this}));
            this.this$0.cancel(true);
        }
    }
}
