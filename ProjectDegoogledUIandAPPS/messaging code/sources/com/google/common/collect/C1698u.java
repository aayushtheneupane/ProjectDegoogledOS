package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.common.collect.u */
abstract class C1698u implements C1635Ya {
    private transient Collection entries;

    /* renamed from: iN */
    private transient Map f2545iN;
    private transient Set keySet;

    C1698u() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: dl */
    public abstract Map mo8584dl();

    /* access modifiers changed from: package-private */
    /* renamed from: el */
    public Collection mo9273el() {
        if (this instanceof C1659gb) {
            return new C1696t(this, (C1690r) null);
        }
        return new C1693s(this);
    }

    public Collection entries() {
        Collection collection = this.entries;
        if (collection != null) {
            return collection;
        }
        Collection el = mo9273el();
        this.entries = el;
        return el;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: fl */
    public abstract Set mo8586fl();

    /* access modifiers changed from: package-private */
    /* renamed from: gl */
    public abstract Iterator mo8800gl();

    /* renamed from: ja */
    public Map mo8589ja() {
        Map map = this.f2545iN;
        if (map != null) {
            return map;
        }
        Map dl = mo8584dl();
        this.f2545iN = dl;
        return dl;
    }

    public Set keySet() {
        Set set = this.keySet;
        if (set != null) {
            return set;
        }
        Set fl = mo8586fl();
        this.keySet = fl;
        return fl;
    }
}
