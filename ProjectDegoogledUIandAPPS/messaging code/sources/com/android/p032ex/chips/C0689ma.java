package com.android.p032ex.chips;

import com.android.p032ex.chips.p033a.C0660b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.android.ex.chips.ma */
class C0689ma implements C0645M {
    final /* synthetic */ C0693oa this$1;

    /* renamed from: vv */
    final /* synthetic */ ArrayList f806vv;

    C0689ma(C0693oa oaVar, ArrayList arrayList) {
        this.this$1 = oaVar;
        this.f806vv = arrayList;
    }

    /* renamed from: a */
    public void mo5444a(Map map) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f806vv.iterator();
        while (it.hasNext()) {
            C0660b bVar = (C0660b) it.next();
            C0699ra access$3600 = (bVar == null || !C0699ra.m1084b(bVar.getEntry().getContactId()) || this.this$1.this$0.getSpannable().getSpanStart(bVar) == -1) ? null : this.this$1.this$0.createValidatedEntry((C0699ra) map.get(C0697qa.tokenizeAddress(bVar.getEntry().getDestination())));
            if (access$3600 != null) {
                arrayList.add(this.this$1.m1076f(access$3600));
            } else {
                arrayList.add((Object) null);
            }
        }
        this.this$1.m1075a((List) this.f806vv, (List) arrayList);
    }

    /* renamed from: a */
    public void mo5445a(Set set) {
        ArrayList arrayList = new ArrayList(set.size());
        Iterator it = this.f806vv.iterator();
        while (it.hasNext()) {
            C0660b bVar = (C0660b) it.next();
            if (bVar == null || !C0699ra.m1084b(bVar.getEntry().getContactId()) || this.this$1.this$0.getSpannable().getSpanStart(bVar) == -1) {
                arrayList.add((Object) null);
            } else if (set.contains(bVar.getEntry().getDestination())) {
                arrayList.add(this.this$1.m1076f(bVar.getEntry()));
            } else {
                arrayList.add((Object) null);
            }
        }
        this.this$1.m1075a((List) this.f806vv, (List) arrayList);
    }
}
