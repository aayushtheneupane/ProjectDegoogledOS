package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.ya */
class C1392ya implements View.OnAttachStateChangeListener {
    final /* synthetic */ C1038Da this$0;

    C1392ya(C1038Da da) {
        this.this$0 = da;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        this.this$0.f1639AG.removeCallbacks(this.this$0.mDismissRunnable);
        this.this$0.mPopupWindow.dismiss();
        C1380sa unused = this.this$0.f1640BG = null;
        C1380sa unused2 = this.this$0.f1641CG = null;
        boolean unused3 = this.this$0.f1642DG = false;
    }
}
