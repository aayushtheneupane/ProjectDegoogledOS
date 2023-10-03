package com.android.p032ex.chips;

import android.widget.PopupWindow;

/* renamed from: com.android.ex.chips.X */
class C0655X implements PopupWindow.OnDismissListener {
    final /* synthetic */ C0697qa this$0;

    C0655X(C0697qa qaVar) {
        this.this$0 = qaVar;
    }

    public void onDismiss() {
        this.this$0.clearSelectedChip();
    }
}
