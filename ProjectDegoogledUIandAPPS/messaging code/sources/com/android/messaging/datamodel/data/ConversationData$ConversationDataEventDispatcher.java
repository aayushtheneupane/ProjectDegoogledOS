package com.android.messaging.datamodel.data;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.Iterator;

class ConversationData$ConversationDataEventDispatcher extends ArrayList implements C0924g {
    final /* synthetic */ C0931n this$0;

    /* synthetic */ ConversationData$ConversationDataEventDispatcher(C0931n nVar, C0923f fVar) {
        this.this$0 = nVar;
    }

    /* renamed from: a */
    public void mo6216a(C0931n nVar, Cursor cursor, C0936s sVar, boolean z) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((C0924g) it.next()).mo6216a(nVar, cursor, sVar, z);
        }
    }

    /* renamed from: b */
    public void mo6218b(String str) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((C0924g) it.next()).mo6218b(str);
        }
    }

    /* renamed from: c */
    public void mo6219c(C0931n nVar) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((C0924g) it.next()).mo6219c(nVar);
        }
    }

    /* renamed from: a */
    public void mo6215a(C0931n nVar) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((C0924g) it.next()).mo6215a(nVar);
        }
    }

    /* renamed from: b */
    public void mo6217b(C0931n nVar) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((C0924g) it.next()).mo6217b(nVar);
        }
    }
}
