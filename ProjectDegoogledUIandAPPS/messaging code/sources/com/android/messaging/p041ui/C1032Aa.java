package com.android.messaging.p041ui;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/* renamed from: com.android.messaging.ui.Aa */
class C1032Aa implements PopupWindow.OnDismissListener {

    /* renamed from: uG */
    final /* synthetic */ View f1560uG;
    final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener val$listener;

    C1032Aa(C1038Da da, View view, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        this.f1560uG = view;
        this.val$listener = onGlobalLayoutListener;
    }

    public void onDismiss() {
        this.f1560uG.getViewTreeObserver().removeOnGlobalLayoutListener(this.val$listener);
    }
}
