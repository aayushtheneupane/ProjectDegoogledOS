package com.android.p032ex.chips;

import android.os.Handler;
import android.os.Message;
import android.widget.ListPopupWindow;

/* renamed from: com.android.ex.chips.W */
class C0654W extends Handler {
    C0654W(C0697qa qaVar) {
    }

    public void handleMessage(Message message) {
        if (message.what == C0697qa.DISMISS) {
            ((ListPopupWindow) message.obj).dismiss();
        } else {
            super.handleMessage(message);
        }
    }
}
