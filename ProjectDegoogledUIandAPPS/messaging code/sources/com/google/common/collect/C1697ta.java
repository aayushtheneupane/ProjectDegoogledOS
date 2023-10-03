package com.google.common.collect;

import com.google.common.collect.MapMakerInternalMap;
import java.util.AbstractQueue;
import java.util.Iterator;

/* renamed from: com.google.common.collect.ta */
final class C1697ta extends AbstractQueue {

    /* renamed from: TP */
    final C1553Aa f2544TP = new C1691ra(this);

    C1697ta() {
    }

    public void clear() {
        C1553Aa s = this.f2544TP.mo8579s();
        while (true) {
            C1553Aa aa = this.f2544TP;
            if (s != aa) {
                C1553Aa s2 = s.mo8579s();
                MapMakerInternalMap.m4306h(s);
                s = s2;
            } else {
                aa.mo8571b(aa);
                C1553Aa aa2 = this.f2544TP;
                aa2.mo8569a(aa2);
                return;
            }
        }
    }

    public boolean contains(Object obj) {
        return ((C1553Aa) obj).mo8579s() != MapMakerInternalMap.NullEntry.INSTANCE;
    }

    public boolean isEmpty() {
        return this.f2544TP.mo8579s() == this.f2544TP;
    }

    public Iterator iterator() {
        C1553Aa s = this.f2544TP.mo8579s();
        if (s == this.f2544TP) {
            s = null;
        }
        return new C1694sa(this, s);
    }

    public boolean offer(Object obj) {
        C1553Aa aa = (C1553Aa) obj;
        MapMakerInternalMap.m4304c(aa.mo8566F(), aa.mo8579s());
        C1553Aa F = this.f2544TP.mo8566F();
        F.mo8571b(aa);
        aa.mo8569a(F);
        C1553Aa aa2 = this.f2544TP;
        aa.mo8571b(aa2);
        aa2.mo8569a(aa);
        return true;
    }

    public Object peek() {
        C1553Aa s = this.f2544TP.mo8579s();
        if (s == this.f2544TP) {
            return null;
        }
        return s;
    }

    public Object poll() {
        C1553Aa s = this.f2544TP.mo8579s();
        if (s == this.f2544TP) {
            return null;
        }
        MapMakerInternalMap.m4304c(s.mo8566F(), s.mo8579s());
        MapMakerInternalMap.m4306h(s);
        MapMakerInternalMap.NullEntry nullEntry = MapMakerInternalMap.NullEntry.INSTANCE;
        return s;
    }

    public boolean remove(Object obj) {
        C1553Aa aa = (C1553Aa) obj;
        C1553Aa F = aa.mo8566F();
        C1553Aa s = aa.mo8579s();
        MapMakerInternalMap.m4304c(F, s);
        MapMakerInternalMap.m4306h(aa);
        return s != MapMakerInternalMap.NullEntry.INSTANCE;
    }

    public int size() {
        int i = 0;
        for (C1553Aa s = this.f2544TP.mo8579s(); s != this.f2544TP; s = s.mo8579s()) {
            i++;
        }
        return i;
    }
}
