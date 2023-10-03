package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.Ca */
class C1036Ca implements Runnable {
    final /* synthetic */ C1038Da this$0;

    /* renamed from: vG */
    final /* synthetic */ C1380sa f1605vG;

    /* renamed from: wG */
    final /* synthetic */ View f1606wG;

    C1036Ca(C1038Da da, View view, C1380sa saVar) {
        this.this$0 = da;
        this.f1606wG = view;
        this.f1605vG = saVar;
    }

    public void run() {
        this.f1606wG.setVisibility(8);
        try {
            this.this$0.mPopupWindow.dismiss();
        } catch (IllegalArgumentException unused) {
        }
        this.f1605vG.mo8000aj().removeOnAttachStateChangeListener(this.this$0.f1645yn);
        C1380sa unused2 = this.this$0.f1640BG = null;
        boolean unused3 = this.this$0.f1642DG = false;
        if (this.this$0.f1641CG != null) {
            C1380sa e = this.this$0.f1641CG;
            C1380sa unused4 = this.this$0.f1641CG = null;
            this.this$0.mo6946e(e);
        }
    }
}
