package com.android.messaging.datamodel.data;

import com.android.messaging.util.C1424b;
import java.util.ArrayList;
import java.util.Iterator;

class DraftMessageData$DraftMessageDataEventDispatcher extends ArrayList implements C0942y {
    final /* synthetic */ C0889A this$0;

    /* synthetic */ DraftMessageData$DraftMessageDataEventDispatcher(C0889A a, C0939v vVar) {
        this.this$0 = a;
    }

    /* renamed from: a */
    public void mo6222a(C0889A a, int i) {
        C1424b.m3593oc();
        Iterator it = iterator();
        while (it.hasNext()) {
            ((C0942y) it.next()).mo6222a(a, i);
        }
    }

    /* renamed from: r */
    public void mo6223r() {
        C1424b.m3593oc();
        Iterator it = iterator();
        while (it.hasNext()) {
            ((C0942y) it.next()).mo6223r();
        }
    }

    /* renamed from: a */
    public void mo6221a(C0889A a) {
        C1424b.m3593oc();
        Iterator it = iterator();
        while (it.hasNext()) {
            ((C0942y) it.next()).mo6221a(a);
        }
    }
}
