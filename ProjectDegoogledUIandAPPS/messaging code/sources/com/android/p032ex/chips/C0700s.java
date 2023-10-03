package com.android.p032ex.chips;

import android.view.View;

/* renamed from: com.android.ex.chips.s */
class C0700s implements View.OnClickListener {
    final /* synthetic */ C0704v this$0;

    C0700s(C0704v vVar) {
        this.this$0 = vVar;
    }

    public void onClick(View view) {
        if (this.this$0.mPermissionRequestDismissedListener != null) {
            this.this$0.mPermissionRequestDismissedListener.onPermissionRequestDismissed();
        }
    }
}
