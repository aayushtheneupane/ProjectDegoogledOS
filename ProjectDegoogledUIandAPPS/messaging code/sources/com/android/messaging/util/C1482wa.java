package com.android.messaging.util;

import android.view.View;

/* renamed from: com.android.messaging.util.wa */
class C1482wa implements View.OnLayoutChangeListener {

    /* renamed from: WK */
    final /* synthetic */ Runnable f2347WK;
    final /* synthetic */ View val$view;

    C1482wa(Runnable runnable, View view) {
        this.f2347WK = runnable;
        this.val$view = view;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        C1480va.getMainThreadHandler().post(this.f2347WK);
        this.val$view.removeOnLayoutChangeListener(this);
    }
}
