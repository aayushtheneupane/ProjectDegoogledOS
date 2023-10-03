package p000;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: hqw */
/* compiled from: PG */
final class hqw extends huj {

    /* renamed from: a */
    public final /* synthetic */ hrc f13280a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public hqw(hrc hrc, Map map) {
        super(map);
        this.f13280a = hrc;
    }

    public final void clear() {
        ife.m12889c(iterator());
    }

    public final boolean containsAll(Collection collection) {
        return this.f13413b.keySet().containsAll(collection);
    }

    public final boolean equals(Object obj) {
        return this == obj || this.f13413b.keySet().equals(obj);
    }

    public final int hashCode() {
        return this.f13413b.keySet().hashCode();
    }

    public final Iterator iterator() {
        return new hqv(this, this.f13413b.entrySet().iterator());
    }

    public final boolean remove(Object obj) {
        Collection collection = (Collection) this.f13413b.remove(obj);
        if (collection == null) {
            return false;
        }
        int size = collection.size();
        collection.clear();
        hrc.m11945b(this.f13280a, size);
        return size > 0;
    }
}
