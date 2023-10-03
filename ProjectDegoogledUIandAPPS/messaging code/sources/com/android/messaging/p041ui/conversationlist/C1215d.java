package com.android.messaging.p041ui.conversationlist;

import com.android.messaging.datamodel.action.C0806U;
import com.android.messaging.datamodel.action.UpdateDestinationBlockedAction;

/* renamed from: com.android.messaging.ui.conversationlist.d */
class C1215d implements Runnable {

    /* renamed from: qH */
    final /* synthetic */ C0806U f1930qH;
    final /* synthetic */ C1216e this$1;

    C1215d(C1216e eVar, C0806U u) {
        this.this$1 = eVar;
        this.f1930qH = u;
    }

    public void run() {
        C1232u uVar = this.this$1.f1931rH;
        UpdateDestinationBlockedAction.m1458a(uVar.f1947CH, false, uVar.f1946BH, this.f1930qH);
    }
}
