package com.android.messaging.p041ui.mediapicker;

import com.android.messaging.datamodel.data.PendingAttachmentData;

/* renamed from: com.android.messaging.ui.mediapicker.ma */
class C1339ma implements Runnable {

    /* renamed from: tI */
    final /* synthetic */ PendingAttachmentData f2127tI;
    final /* synthetic */ C1345pa this$0;

    C1339ma(C1345pa paVar, PendingAttachmentData pendingAttachmentData) {
        this.this$0 = paVar;
        this.f2127tI = pendingAttachmentData;
    }

    public void run() {
        this.this$0.mListener.mo7452a(this.f2127tI);
    }
}
