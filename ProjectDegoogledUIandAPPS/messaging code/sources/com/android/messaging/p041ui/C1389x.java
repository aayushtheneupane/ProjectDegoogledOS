package com.android.messaging.p041ui;

import android.content.DialogInterface;

/* renamed from: com.android.messaging.ui.x */
class C1389x implements DialogInterface.OnClickListener {
    final /* synthetic */ ClassZeroActivity this$0;

    C1389x(ClassZeroActivity classZeroActivity) {
        this.this$0 = classZeroActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        boolean unused = this.this$0.f1608_b = true;
        ClassZeroActivity.m2523b(this.this$0);
        dialogInterface.dismiss();
        ClassZeroActivity.m2524c(this.this$0);
    }
}
