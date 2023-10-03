package com.android.p032ex.chips;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.android.ex.chips.e */
final class C0672e extends Handler {
    final /* synthetic */ C0684k this$0;

    /* synthetic */ C0672e(C0684k kVar, C0666b bVar) {
        this.this$0 = kVar;
    }

    public void handleMessage(Message message) {
        if (this.this$0.mRemainingDirectoryCount > 0) {
            C0684k kVar = this.this$0;
            kVar.updateEntries(kVar.constructEntryList());
        }
    }
}
