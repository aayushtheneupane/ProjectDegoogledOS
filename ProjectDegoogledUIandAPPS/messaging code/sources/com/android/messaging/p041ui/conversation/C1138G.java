package com.android.messaging.p041ui.conversation;

import androidx.recyclerview.widget.C0586qa;

/* renamed from: com.android.messaging.ui.conversation.G */
class C1138G implements Runnable {
    final /* synthetic */ C1139H this$1;
    final /* synthetic */ C0586qa val$holder;
    final /* synthetic */ ConversationMessageView val$view;

    C1138G(C1139H h, ConversationMessageView conversationMessageView, C0586qa qaVar) {
        this.this$1 = h;
        this.val$view = conversationMessageView;
        this.val$holder = qaVar;
    }

    public void run() {
        this.val$view.setAlpha(1.0f);
        this.this$1.mo5250q(this.val$holder);
    }
}
