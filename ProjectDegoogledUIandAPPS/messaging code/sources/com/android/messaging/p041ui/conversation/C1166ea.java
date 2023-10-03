package com.android.messaging.p041ui.conversation;

import android.view.View;

/* renamed from: com.android.messaging.ui.conversation.ea */
class C1166ea implements View.OnLongClickListener {
    final /* synthetic */ ConversationMessageView this$0;

    C1166ea(ConversationMessageView conversationMessageView) {
        this.this$0 = conversationMessageView;
    }

    public boolean onLongClick(View view) {
        this.this$0.performLongClick();
        return true;
    }
}
