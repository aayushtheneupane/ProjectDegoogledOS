package com.android.p032ex.chips;

import com.android.p032ex.chips.p033a.C0660b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: com.android.ex.chips.ea */
class C0673ea implements C0645M {
    final /* synthetic */ C0675fa this$1;

    /* renamed from: uv */
    final /* synthetic */ ArrayList f791uv;

    C0673ea(C0675fa faVar, ArrayList arrayList) {
        this.this$1 = faVar;
        this.f791uv = arrayList;
    }

    /* renamed from: a */
    public void mo5444a(Map map) {
        C0699ra access$3600;
        Iterator it = this.f791uv.iterator();
        while (it.hasNext()) {
            C0660b bVar = (C0660b) it.next();
            if (!(!C0699ra.m1084b(bVar.getEntry().getContactId()) || this.this$1.this$0.getSpannable().getSpanStart(bVar) == -1 || (access$3600 = this.this$1.this$0.createValidatedEntry((C0699ra) map.get(C0697qa.tokenizeAddress(bVar.getEntry().getDestination()).toLowerCase()))) == null)) {
                this.this$1.this$0.mHandler.post(new C0671da(this, bVar, access$3600));
            }
        }
    }

    /* renamed from: a */
    public void mo5445a(Set set) {
    }
}
