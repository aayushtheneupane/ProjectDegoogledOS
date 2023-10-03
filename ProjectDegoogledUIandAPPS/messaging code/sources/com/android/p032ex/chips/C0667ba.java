package com.android.p032ex.chips;

import android.content.DialogInterface;

/* renamed from: com.android.ex.chips.ba */
class C0667ba implements DialogInterface.OnDismissListener {
    final /* synthetic */ C0697qa this$0;

    C0667ba(C0697qa qaVar) {
        this.this$0 = qaVar;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        String unused = this.this$0.mCurrentWarningText = "";
    }
}
