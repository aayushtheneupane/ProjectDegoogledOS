package com.android.messaging.p041ui.p042a;

import com.android.messaging.util.C1480va;

/* renamed from: com.android.messaging.ui.a.d */
class C1077d implements Runnable {
    final /* synthetic */ C1079f this$0;

    C1077d(C1079f fVar) {
        this.this$0 = fVar;
    }

    public void run() {
        try {
            this.this$0.mPopupWindow.dismiss();
        } catch (IllegalArgumentException unused) {
        }
        C1480va.getMainThreadHandler().removeCallbacks(this.this$0.f1708kl);
    }
}
