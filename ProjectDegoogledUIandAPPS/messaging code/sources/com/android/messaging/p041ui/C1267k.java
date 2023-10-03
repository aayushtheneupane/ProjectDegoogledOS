package com.android.messaging.p041ui;

import com.android.messaging.datamodel.data.C0909V;
import com.android.messaging.datamodel.data.C0921da;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.ui.k */
class C1267k implements C1117ca {

    /* renamed from: mF */
    final /* synthetic */ PersonItemView f1987mF;

    C1267k(PersonItemView personItemView) {
        this.f1987mF = personItemView;
    }

    /* renamed from: a */
    public void mo7224a(C0909V v) {
        C1424b.m3592ia(v instanceof C0921da);
        C0921da daVar = (C0921da) v;
        if (daVar.mo6428wf()) {
            C1040Ea.get().mo6976f(this.f1987mF.getContext(), daVar.mo6427vf());
        }
    }

    /* renamed from: c */
    public boolean mo7225c(C0909V v) {
        return false;
    }
}
