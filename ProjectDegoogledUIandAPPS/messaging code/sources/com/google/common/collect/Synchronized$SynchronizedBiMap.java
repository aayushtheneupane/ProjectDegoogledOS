package com.google.common.collect;

import java.io.Serializable;
import java.util.Set;

class Synchronized$SynchronizedBiMap extends Synchronized$SynchronizedMap implements C1708z, Serializable {
    private static final long serialVersionUID = 0;

    /* renamed from: cO */
    private transient Set f2498cO;

    /* access modifiers changed from: package-private */
    /* renamed from: ml */
    public C1708z m4502ml() {
        return (C1708z) this.delegate;
    }

    public Set values() {
        Set set;
        synchronized (this.mutex) {
            if (this.f2498cO == null) {
                this.f2498cO = new Synchronized$SynchronizedSet(m4502ml().values(), this.mutex);
            }
            set = this.f2498cO;
        }
        return set;
    }
}
