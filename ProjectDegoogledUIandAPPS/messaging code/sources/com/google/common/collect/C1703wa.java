package com.google.common.collect;

import com.google.common.collect.MapMakerInternalMap;
import java.util.AbstractQueue;
import java.util.Iterator;

/* renamed from: com.google.common.collect.wa */
final class C1703wa extends AbstractQueue {

    /* renamed from: TP */
    final C1553Aa f2550TP = new C1699ua(this);

    C1703wa() {
    }

    public void clear() {
        C1553Aa t = this.f2550TP.mo8580t();
        while (true) {
            C1553Aa aa = this.f2550TP;
            if (t != aa) {
                C1553Aa t2 = t.mo8580t();
                MapMakerInternalMap.m4307i(t);
                t = t2;
            } else {
                aa.mo8572c(aa);
                C1553Aa aa2 = this.f2550TP;
                aa2.mo8573d(aa2);
                return;
            }
        }
    }

    public boolean contains(Object obj) {
        return ((C1553Aa) obj).mo8580t() != MapMakerInternalMap.NullEntry.INSTANCE;
    }

    public boolean isEmpty() {
        return this.f2550TP.mo8580t() == this.f2550TP;
    }

    public Iterator iterator() {
        C1553Aa t = this.f2550TP.mo8580t();
        if (t == this.f2550TP) {
            t = null;
        }
        return new C1701va(this, t);
    }

    public boolean offer(Object obj) {
        C1553Aa aa = (C1553Aa) obj;
        MapMakerInternalMap.m4305d(aa.mo8567P(), aa.mo8580t());
        C1553Aa P = this.f2550TP.mo8567P();
        P.mo8572c(aa);
        aa.mo8573d(P);
        C1553Aa aa2 = this.f2550TP;
        aa.mo8572c(aa2);
        aa2.mo8573d(aa);
        return true;
    }

    public Object peek() {
        C1553Aa t = this.f2550TP.mo8580t();
        if (t == this.f2550TP) {
            return null;
        }
        return t;
    }

    public Object poll() {
        C1553Aa t = this.f2550TP.mo8580t();
        if (t == this.f2550TP) {
            return null;
        }
        MapMakerInternalMap.m4305d(t.mo8567P(), t.mo8580t());
        MapMakerInternalMap.m4307i(t);
        MapMakerInternalMap.NullEntry nullEntry = MapMakerInternalMap.NullEntry.INSTANCE;
        return t;
    }

    public boolean remove(Object obj) {
        C1553Aa aa = (C1553Aa) obj;
        C1553Aa P = aa.mo8567P();
        C1553Aa t = aa.mo8580t();
        MapMakerInternalMap.m4305d(P, t);
        MapMakerInternalMap.m4307i(aa);
        return t != MapMakerInternalMap.NullEntry.INSTANCE;
    }

    public int size() {
        int i = 0;
        for (C1553Aa t = this.f2550TP.mo8580t(); t != this.f2550TP; t = t.mo8580t()) {
            i++;
        }
        return i;
    }
}
