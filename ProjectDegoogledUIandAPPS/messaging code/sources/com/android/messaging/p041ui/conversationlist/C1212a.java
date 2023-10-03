package com.android.messaging.p041ui.conversationlist;

import android.app.Activity;
import com.android.messaging.p041ui.C1040Ea;

/* renamed from: com.android.messaging.ui.conversationlist.a */
class C1212a implements Runnable {
    final /* synthetic */ AbstractConversationListActivity this$0;
    final /* synthetic */ Activity val$activity;

    C1212a(AbstractConversationListActivity abstractConversationListActivity, Activity activity) {
        this.this$0 = abstractConversationListActivity;
        this.val$activity = activity;
    }

    public void run() {
        this.this$0.startActivityForResult(C1040Ea.get().mo6963b(this.val$activity), 1);
    }
}
