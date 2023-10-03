package com.android.messaging.p041ui.conversationlist;

import android.content.DialogInterface;
import com.android.messaging.datamodel.action.DeleteConversationAction;
import java.util.Collection;

/* renamed from: com.android.messaging.ui.conversationlist.b */
class C1213b implements DialogInterface.OnClickListener {

    /* renamed from: nH */
    final /* synthetic */ Collection f1927nH;
    final /* synthetic */ AbstractConversationListActivity this$0;

    C1213b(AbstractConversationListActivity abstractConversationListActivity, Collection collection) {
        this.this$0 = abstractConversationListActivity;
        this.f1927nH = collection;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        for (C1232u uVar : this.f1927nH) {
            DeleteConversationAction.m1351a(uVar.f1946BH, uVar.timestamp);
        }
        this.this$0.mo7526bb();
    }
}
