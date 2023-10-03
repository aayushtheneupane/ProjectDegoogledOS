package com.android.messaging.p041ui.p042a;

import com.android.messaging.util.C1480va;
import com.android.messaging.util.C1486ya;

/* renamed from: com.android.messaging.ui.a.c */
class C1076c implements Runnable {

    /* renamed from: FG */
    boolean f1696FG = false;

    /* renamed from: GG */
    boolean f1697GG = true;
    final /* synthetic */ C1079f this$0;

    C1076c(C1079f fVar) {
        this.this$0 = fVar;
    }

    public void run() {
        if (!this.f1696FG) {
            this.this$0.f1698al.getGlobalVisibleRect(this.this$0.f1701dl);
            if (this.this$0.f1701dl.width() > 1 && this.this$0.f1701dl.height() > 1) {
                this.f1696FG = true;
                this.this$0.f1698al.startAnimation(this.this$0);
                this.this$0.f1698al.invalidate();
                C1480va.getMainThreadHandler().postDelayed(this.this$0.f1708kl, this.this$0.getDuration() * 2);
            } else if (!this.f1697GG) {
                this.this$0.f1698al.setAlpha(1.0f);
                this.this$0.f1698al.setVisibility(0);
            } else {
                this.f1697GG = false;
                C1486ya.m3855a(this.this$0.f1698al, (Runnable) this);
            }
        }
    }
}
