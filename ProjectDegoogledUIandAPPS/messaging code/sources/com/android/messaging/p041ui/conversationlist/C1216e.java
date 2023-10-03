package com.android.messaging.p041ui.conversationlist;

import android.content.DialogInterface;
import android.view.View;
import com.android.messaging.datamodel.action.UpdateDestinationBlockedAction;
import java.util.List;

/* renamed from: com.android.messaging.ui.conversationlist.e */
class C1216e implements DialogInterface.OnClickListener {

    /* renamed from: rH */
    final /* synthetic */ C1232u f1931rH;
    final /* synthetic */ AbstractConversationListActivity this$0;

    C1216e(AbstractConversationListActivity abstractConversationListActivity, C1232u uVar) {
        this.this$0 = abstractConversationListActivity;
        this.f1931rH = uVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        AbstractConversationListActivity abstractConversationListActivity = this.this$0;
        View findViewById = abstractConversationListActivity.findViewById(16908298);
        List b = this.this$0.f1895Xb.mo7546b();
        C1217f fVar = new C1217f(abstractConversationListActivity, findViewById, new C1215d(this, new C1217f(abstractConversationListActivity, findViewById, (Runnable) null, b)), b);
        C1232u uVar = this.f1931rH;
        UpdateDestinationBlockedAction.m1458a(uVar.f1947CH, true, uVar.f1946BH, fVar);
        this.this$0.mo7526bb();
    }
}
