package com.android.messaging.mmslib.p039a;

import java.util.Vector;

/* renamed from: com.android.messaging.mmslib.a.j */
public class C0980j {

    /* renamed from: _l */
    private Vector f1405_l;

    public C0980j() {
        this.f1405_l = null;
        this.f1405_l = new Vector();
    }

    /* renamed from: a */
    public boolean mo6689a(C0989s sVar) {
        if (sVar != null) {
            return this.f1405_l.add(sVar);
        }
        throw new NullPointerException();
    }

    /* renamed from: bi */
    public int mo6690bi() {
        return this.f1405_l.size();
    }

    public C0989s getPart(int i) {
        return (C0989s) this.f1405_l.get(i);
    }

    public void removeAll() {
        this.f1405_l.clear();
    }

    /* renamed from: a */
    public void mo6688a(int i, C0989s sVar) {
        if (sVar != null) {
            this.f1405_l.add(i, sVar);
            return;
        }
        throw new NullPointerException();
    }
}
