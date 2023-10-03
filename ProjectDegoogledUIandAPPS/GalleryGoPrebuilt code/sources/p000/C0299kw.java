package p000;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import p003j$.util.Collection$$CC;
import p003j$.util.Set$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: kw */
/* compiled from: PG */
final class C0299kw implements Set, p003j$.util.Set {

    /* renamed from: a */
    private final /* synthetic */ C0304la f15171a;

    public C0299kw(C0304la laVar) {
        this.f15171a = laVar;
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
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection collection) {
        int a = this.f15171a.mo9184a();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            this.f15171a.mo9189a(entry.getKey(), entry.getValue());
        }
        return a != this.f15171a.mo9184a();
    }

    public final void clear() {
        this.f15171a.mo9192c();
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            int a = this.f15171a.mo9185a(entry.getKey());
            if (a >= 0) {
                return C0294kr.m14539a(this.f15171a.mo9186a(a, 1), entry.getValue());
            }
        }
        return false;
    }

    public final boolean containsAll(Collection collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public final boolean equals(Object obj) {
        return C0304la.m14562a((Set) this, obj);
    }

    public final int hashCode() {
        int i = 0;
        for (int a = this.f15171a.mo9184a() - 1; a >= 0; a--) {
            Object a2 = this.f15171a.mo9186a(a, 0);
            Object a3 = this.f15171a.mo9186a(a, 1);
            i += (a2 != null ? a2.hashCode() : 0) ^ (a3 != null ? a3.hashCode() : 0);
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.f15171a.mo9184a() == 0;
    }

    public final Iterator iterator() {
        return new C0301ky(this.f15171a);
    }

    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final int size() {
        return this.f15171a.mo9184a();
    }

    public final Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray(Object[] objArr) {
        throw new UnsupportedOperationException();
    }
}
