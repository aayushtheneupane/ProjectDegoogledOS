package com.android.messaging.p041ui.mediapicker.camerafocus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: com.android.messaging.ui.mediapicker.camerafocus.b */
class C1305b extends Handler {
    final /* synthetic */ C1306c this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1305b(C1306c cVar, Looper looper) {
        super(looper);
        this.this$0 = cVar;
    }

    public void handleMessage(Message message) {
        if (message.what == 0) {
            this.this$0.cancelAutoFocus();
        }
    }
}
