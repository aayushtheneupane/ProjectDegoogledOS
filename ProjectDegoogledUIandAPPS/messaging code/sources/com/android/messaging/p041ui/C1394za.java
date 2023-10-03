package com.android.messaging.p041ui;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: com.android.messaging.ui.za */
class C1394za implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ C1038Da this$0;

    /* renamed from: uG */
    final /* synthetic */ View f2202uG;

    /* renamed from: vG */
    final /* synthetic */ C1380sa f2203vG;

    C1394za(C1038Da da, View view, C1380sa saVar) {
        this.this$0 = da;
        this.f2202uG = view;
        this.f2203vG = saVar;
    }

    public void onGlobalLayout() {
        this.this$0.mPopupWindow.update(this.f2202uG, 0, this.this$0.m2556f(this.f2203vG), this.f2202uG.getWidth(), -2);
    }
}
