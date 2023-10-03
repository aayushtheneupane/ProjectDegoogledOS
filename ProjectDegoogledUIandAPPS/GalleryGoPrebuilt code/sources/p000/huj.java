package p000;

import java.util.Iterator;
import java.util.Map;

/* renamed from: huj */
/* compiled from: PG */
class huj extends hvm {

    /* renamed from: b */
    public final Map f13413b;

    public huj(Map map) {
        this.f13413b = (Map) ife.m12898e((Object) map);
    }

    public void clear() {
        this.f13413b.clear();
    }

    public final boolean contains(Object obj) {
        return this.f13413b.containsKey(obj);
    }

    public final boolean isEmpty() {
        return this.f13413b.isEmpty();
    }

    public Iterator iterator() {
        return new hue(this.f13413b.entrySet().iterator());
    }

    public boolean remove(Object obj) {
        if (!contains(obj)) {
            return false;
        }
        this.f13413b.remove(obj);
        return true;
    }

    public final int size() {
        return this.f13413b.size();
    }
}
