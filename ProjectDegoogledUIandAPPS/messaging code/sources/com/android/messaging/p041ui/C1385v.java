package com.android.messaging.p041ui;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.android.messaging.ui.v */
class C1385v extends Handler {
    final /* synthetic */ ClassZeroActivity this$0;

    C1385v(ClassZeroActivity classZeroActivity) {
        this.this$0 = classZeroActivity;
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            boolean unused = this.this$0.f1608_b = false;
            this.this$0.mDialog.dismiss();
            ClassZeroActivity.m2523b(this.this$0);
            ClassZeroActivity.m2524c(this.this$0);
        }
    }
}
