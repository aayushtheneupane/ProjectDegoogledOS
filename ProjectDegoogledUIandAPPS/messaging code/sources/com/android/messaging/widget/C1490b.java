package com.android.messaging.widget;

import android.content.Context;

/* renamed from: com.android.messaging.widget.b */
class C1490b implements Runnable {

    /* renamed from: lM */
    final /* synthetic */ int f2365lM;
    final /* synthetic */ Context val$context;

    C1490b(BugleWidgetProvider bugleWidgetProvider, Context context, int i) {
        this.val$context = context;
        this.f2365lM = i;
    }

    public void run() {
        BugleWidgetProvider.m3883b(this.val$context, this.f2365lM);
    }
}
