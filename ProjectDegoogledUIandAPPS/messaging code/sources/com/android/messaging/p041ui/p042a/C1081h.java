package com.android.messaging.p041ui.p042a;

import android.graphics.drawable.Drawable;
import android.view.ViewGroupOverlay;
import android.widget.FrameLayout;

/* renamed from: com.android.messaging.ui.a.h */
class C1081h implements Runnable {

    /* renamed from: HG */
    final /* synthetic */ ViewGroupOverlay f1709HG;

    /* renamed from: IG */
    final /* synthetic */ FrameLayout f1710IG;

    /* renamed from: JG */
    final /* synthetic */ Drawable f1711JG;
    final /* synthetic */ C1082i this$0;

    C1081h(C1082i iVar, ViewGroupOverlay viewGroupOverlay, FrameLayout frameLayout, Drawable drawable) {
        this.this$0 = iVar;
        this.f1709HG = viewGroupOverlay;
        this.f1710IG = frameLayout;
        this.f1711JG = drawable;
    }

    public void run() {
        this.f1709HG.remove(this.f1710IG);
        this.this$0.f1714al.setBackground(this.f1711JG);
        if (this.this$0.f1713LG != null) {
            this.this$0.f1713LG.recycle();
        }
    }
}
