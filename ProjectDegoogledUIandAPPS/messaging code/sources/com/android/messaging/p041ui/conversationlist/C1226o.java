package com.android.messaging.p041ui.conversationlist;

import com.android.messaging.datamodel.action.UpdateConversationArchiveStatusAction;

/* renamed from: com.android.messaging.ui.conversationlist.o */
class C1226o implements Runnable {

    /* renamed from: SG */
    final /* synthetic */ String f1936SG;

    C1226o(ConversationListItemView conversationListItemView, String str) {
        this.f1936SG = str;
    }

    public void run() {
        UpdateConversationArchiveStatusAction.m1456U(this.f1936SG);
    }
}
