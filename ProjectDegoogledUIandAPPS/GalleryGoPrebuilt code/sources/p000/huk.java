package p000;

import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: huk */
/* compiled from: PG */
final class huk extends AbstractCollection implements Collection {

    /* renamed from: a */
    private final Map f13414a;

    public huk(Map map) {
        this.f13414a = (Map) ife.m12898e((Object) map);
    }

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public final Spliterator spliterator() {
        return Collection$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    public final void clear() {
        this.f13414a.clear();
    }

    public final boolean contains(Object obj) {
        return this.f13414a.containsValue(obj);
    }

    public final boolean isEmpty() {
        return this.f13414a.isEmpty();
    }

    public final Iterator iterator() {
        return new huf(this.f13414a.entrySet().iterator());
    }

    public final boolean remove(Object obj) {
        try {
            return super.remove(obj);
        } catch (UnsupportedOperationException e) {
            for (Map.Entry entry : this.f13414a.entrySet()) {
                if (ife.m12891c(obj, entry.getValue())) {
                    this.f13414a.remove(entry.getKey());
                    return true;
                }
            }
            return false;
        }
    }

    public final boolean removeAll(java.util.Collection collection) {
        try {
            return super.removeAll((java.util.Collection) ife.m12898e((Object) collection));
        } catch (UnsupportedOperationException e) {
            HashSet e2 = ife.m12900e();
            for (Map.Entry entry : this.f13414a.entrySet()) {
                if (collection.contains(entry.getValue())) {
                    e2.add(entry.getKey());
                }
            }
            return this.f13414a.keySet().removeAll(e2);
        }
    }

    public final boolean retainAll(java.util.Collection collection) {
        try {
            return super.retainAll((java.util.Collection) ife.m12898e((Object) collection));
        } catch (UnsupportedOperationException e) {
            HashSet e2 = ife.m12900e();
            for (Map.Entry entry : this.f13414a.entrySet()) {
                if (collection.contains(entry.getValue())) {
                    e2.add(entry.getKey());
                }
            }
            return this.f13414a.keySet().retainAll(e2);
        }
    }

    public final int size() {
        return this.f13414a.size();
    }
}
