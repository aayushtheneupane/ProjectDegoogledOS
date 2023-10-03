package com.android.messaging.datamodel;

import com.android.messaging.util.C1467p;
import com.android.messaging.util.C1472ra;
import com.android.messaging.util.C1474sa;

/* renamed from: com.android.messaging.datamodel.j */
class C0949j implements C1472ra {
    final /* synthetic */ C0950k this$0;

    C0949j(C0950k kVar) {
        this.this$0 = kVar;
    }

    /* renamed from: a */
    public /* synthetic */ C1467p mo6613a(Integer num) {
        return new C1467p(this.this$0.mContext, num.intValue());
    }

    /* renamed from: c */
    public void mo6074c(int i) {
        if (i <= -1) {
            i = C1474sa.getDefault().getDefaultSmsSubscriptionId();
        }
        C0950k.f1354Jx.computeIfAbsent(Integer.valueOf(i), new C0780a(this));
    }
}
