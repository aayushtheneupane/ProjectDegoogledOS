package com.android.messaging.mmslib.p040b;

import p000a.p005b.C0027n;

/* renamed from: com.android.messaging.mmslib.b.c */
public abstract class C0998c {

    /* renamed from: DD */
    private final C0027n f1446DD = new C0027n();

    protected C0998c() {
    }

    /* renamed from: ei */
    public void mo6758ei() {
        this.f1446DD.clear();
    }

    public Object get(Object obj) {
        C0997b bVar;
        if (obj == null || (bVar = (C0997b) this.f1446DD.get(obj)) == null) {
            return null;
        }
        bVar.f1445CD++;
        return bVar.value;
    }

    public boolean put(Object obj, Object obj2) {
        if (this.f1446DD.size() >= 500 || obj == null) {
            return false;
        }
        C0997b bVar = new C0997b((C0996a) null);
        bVar.value = obj2;
        this.f1446DD.put(obj, bVar);
        return true;
    }

    /* renamed from: s */
    public Object mo6761s(Object obj) {
        C0997b bVar = (C0997b) this.f1446DD.remove(obj);
        if (bVar != null) {
            return bVar.value;
        }
        return null;
    }
}
