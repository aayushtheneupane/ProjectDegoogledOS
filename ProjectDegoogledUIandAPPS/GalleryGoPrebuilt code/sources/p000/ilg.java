package p000;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.Set;
import p003j$.util.Set$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: ilg */
/* compiled from: PG */
final class ilg extends AbstractSet implements Set, Collection {

    /* renamed from: a */
    private final /* synthetic */ ilh f14441a;

    public /* synthetic */ ilg(ilh ilh) {
        this.f14441a = ilh;
    }

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public final Spliterator spliterator() {
        return Set$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.f14441a.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public final void clear() {
        this.f14441a.clear();
    }

    public final boolean contains(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.f14441a.get(entry.getKey());
        Object value = entry.getValue();
        if (obj2 == value || (obj2 != null && obj2.equals(value))) {
            return true;
        }
        return false;
    }

    public final Iterator iterator() {
        return new ilf(this.f14441a);
    }

    public final boolean remove(Object obj) {
        Map.Entry entry = (Map.Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.f14441a.remove(entry.getKey());
        return true;
    }

    public final int size() {
        return this.f14441a.size();
    }
}
