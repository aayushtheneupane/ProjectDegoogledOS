package com.android.messaging.p041ui.conversationlist;

import com.android.messaging.datamodel.action.UpdateConversationArchiveStatusAction;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.android.messaging.ui.conversationlist.c */
class C1214c implements Runnable {

    /* renamed from: oH */
    final /* synthetic */ ArrayList f1928oH;

    /* renamed from: pH */
    final /* synthetic */ boolean f1929pH;

    C1214c(AbstractConversationListActivity abstractConversationListActivity, ArrayList arrayList, boolean z) {
        this.f1928oH = arrayList;
        this.f1929pH = z;
    }

    public void run() {
        Iterator it = this.f1928oH.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (this.f1929pH) {
                UpdateConversationArchiveStatusAction.m1456U(str);
            } else {
                UpdateConversationArchiveStatusAction.m1455T(str);
            }
        }
    }
}
