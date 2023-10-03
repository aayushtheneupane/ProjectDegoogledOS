package com.google.common.collect;

import com.google.common.base.C1508E;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

class MapMaker$NullConcurrentMap extends AbstractMap implements ConcurrentMap, Serializable {
    private static final long serialVersionUID = 0;
    private final MapMaker$RemovalCause removalCause;
    private final C1670ka removalListener;

    MapMaker$NullConcurrentMap(C1673la laVar) {
        this.removalListener = (C1670ka) C1508E.m3964e(laVar.removalListener, GenericMapMaker$NullListener.INSTANCE);
        this.removalCause = laVar.f2539NN;
    }

    public boolean containsKey(Object obj) {
        return false;
    }

    public boolean containsValue(Object obj) {
        return false;
    }

    public Set entrySet() {
        return Collections.emptySet();
    }

    public Object get(Object obj) {
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public void mo8829k(Object obj, Object obj2) {
        ((GenericMapMaker$NullListener) this.removalListener).mo8673a(new MapMaker$RemovalNotification(obj, obj2, this.removalCause));
    }

    public Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj2 != null) {
            mo8829k(obj, obj2);
            return null;
        } else {
            throw new NullPointerException();
        }
    }

    public Object putIfAbsent(Object obj, Object obj2) {
        return put(obj, obj2);
    }

    public Object remove(Object obj) {
        return null;
    }

    public boolean remove(Object obj, Object obj2) {
        return false;
    }

    public Object replace(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj2 != null) {
            return null;
        } else {
            throw new NullPointerException();
        }
    }

    public boolean replace(Object obj, Object obj2, Object obj3) {
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj3 != null) {
            return false;
        } else {
            throw new NullPointerException();
        }
    }
}
