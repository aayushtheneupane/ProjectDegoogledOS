package com.android.messaging.widget;

import android.content.Context;

/* renamed from: com.android.messaging.widget.d */
class C1492d implements Runnable {

    /* renamed from: lM */
    final /* synthetic */ int f2366lM;
    final /* synthetic */ Context val$context;

    C1492d(Context context, int i) {
        this.val$context = context;
        this.f2366lM = i;
    }

    public void run() {
        WidgetConversationProvider.m3887b(this.val$context, this.f2366lM);
    }
}
