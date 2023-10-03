package com.android.messaging.p041ui;

import android.content.DialogInterface;

/* renamed from: com.android.messaging.ui.w */
class C1387w implements DialogInterface.OnClickListener {
    final /* synthetic */ ClassZeroActivity this$0;

    C1387w(ClassZeroActivity classZeroActivity) {
        this.this$0 = classZeroActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        ClassZeroActivity.m2524c(this.this$0);
    }
}
