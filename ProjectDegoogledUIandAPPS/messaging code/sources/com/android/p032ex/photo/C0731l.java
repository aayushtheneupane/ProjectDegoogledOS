package com.android.p032ex.photo;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: com.android.ex.photo.l */
class C0731l implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: Yv */
    final /* synthetic */ View f875Yv;
    final /* synthetic */ C0734o this$0;

    C0731l(C0734o oVar, View view) {
        this.this$0 = oVar;
        this.f875Yv = view;
    }

    public void onGlobalLayout() {
        int i = Build.VERSION.SDK_INT;
        this.f875Yv.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        this.this$0.m1157Xn();
    }
}
