package p000;

import java.util.AbstractSet;
import java.util.Iterator;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.Set;
import p003j$.util.Set$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: hrr */
/* compiled from: PG */
final class hrr extends AbstractSet implements Set, Collection {

    /* renamed from: a */
    private final /* synthetic */ hru f13317a;

    public hrr(hru hru) {
        this.f13317a = hru;
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

    public final void clear() {
        this.f13317a.clear();
    }

    public final boolean contains(Object obj) {
        return this.f13317a.containsKey(obj);
    }

    public final Iterator iterator() {
        return new hrm(this.f13317a);
    }

    public final boolean remove(Object obj) {
        hru hru = this.f13317a;
        Object obj2 = hru.f13322a;
        return hru.mo7835b(obj) != hru.f13322a;
    }

    public final int size() {
        hru hru = this.f13317a;
        Object obj = hru.f13322a;
        return hru.f13328g;
    }
}
