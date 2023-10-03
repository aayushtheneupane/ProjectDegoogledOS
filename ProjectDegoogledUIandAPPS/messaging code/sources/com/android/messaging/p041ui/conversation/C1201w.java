package com.android.messaging.p041ui.conversation;

import android.content.DialogInterface;

/* renamed from: com.android.messaging.ui.conversation.w */
class C1201w implements DialogInterface.OnCancelListener {
    final /* synthetic */ C1146O this$0;

    C1201w(C1146O o) {
        this.this$0 = o;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.this$0.mHost.mo7370G();
    }
}
