package com.android.messaging.p041ui.conversation;

import com.android.messaging.datamodel.data.MessageData;

/* renamed from: com.android.messaging.ui.conversation.K */
class C1142K implements Runnable {

    /* renamed from: Xy */
    final /* synthetic */ MessageData f1823Xy;
    final /* synthetic */ C1146O this$0;

    C1142K(C1146O o, MessageData messageData) {
        this.this$0 = o;
        this.f1823Xy = messageData;
    }

    public void run() {
        this.this$0.mo7389a(this.f1823Xy);
    }
}
