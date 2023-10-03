package com.android.messaging.p041ui.conversation;

import android.view.View;
import com.android.messaging.datamodel.data.MessagePartData;

/* renamed from: com.android.messaging.ui.conversation.E */
class C1136E implements View.OnLongClickListener {
    final /* synthetic */ C1146O this$0;

    C1136E(C1146O o) {
        this.this$0 = o;
    }

    public boolean onLongClick(View view) {
        this.this$0.m2856a((ConversationMessageView) view, (MessagePartData) null);
        return true;
    }
}
