package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.common.collect.c */
class C1645c extends C1615Sa {
    final /* synthetic */ C1651e this$1;

    C1645c(C1651e eVar) {
        this.this$1 = eVar;
    }

    public boolean contains(Object obj) {
        return C1552A.m4039a(this.this$1.f2520_P.entrySet(), obj);
    }

    public Iterator iterator() {
        return new C1648d(this.this$1);
    }

    /* access modifiers changed from: package-private */
    public Map map() {
        return this.this$1;
    }

    public boolean remove(Object obj) {
        if (!C1552A.m4039a(this.this$1.f2520_P.entrySet(), obj)) {
            return false;
        }
        AbstractMapBasedMultimap.m4054a(this.this$1.this$0, ((Map.Entry) obj).getKey());
        return true;
    }
}
