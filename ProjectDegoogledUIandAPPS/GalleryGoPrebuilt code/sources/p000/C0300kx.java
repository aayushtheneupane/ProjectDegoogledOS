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

/* renamed from: kx */
/* compiled from: PG */
final class C0300kx implements Set, p003j$.util.Set {

    /* renamed from: a */
    private final /* synthetic */ C0304la f15172a;

    public C0300kx(C0304la laVar) {
        this.f15172a = laVar;
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

    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        this.f15172a.mo9192c();
    }

    public final boolean contains(Object obj) {
        return this.f15172a.mo9185a(obj) >= 0;
    }

    public final boolean containsAll(Collection collection) {
        Map b = this.f15172a.mo9191b();
        for (Object containsKey : collection) {
            if (!b.containsKey(containsKey)) {
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
        for (int a = this.f15172a.mo9184a() - 1; a >= 0; a--) {
            Object a2 = this.f15172a.mo9186a(a, 0);
            i += a2 != null ? a2.hashCode() : 0;
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.f15172a.mo9184a() == 0;
    }

    public final Iterator iterator() {
        return new C0298kv(this.f15172a, 0);
    }

    public final boolean remove(Object obj) {
        int a = this.f15172a.mo9185a(obj);
        if (a < 0) {
            return false;
        }
        this.f15172a.mo9188a(a);
        return true;
    }

    public final boolean removeAll(Collection collection) {
        Map b = this.f15172a.mo9191b();
        int size = b.size();
        for (Object remove : collection) {
            b.remove(remove);
        }
        return size != b.size();
    }

    public final boolean retainAll(Collection collection) {
        return C0304la.m14561a(this.f15172a.mo9191b(), collection);
    }

    public final int size() {
        return this.f15172a.mo9184a();
    }

    public final Object[] toArray() {
        return this.f15172a.mo9312b(0);
    }

    public final Object[] toArray(Object[] objArr) {
        return this.f15172a.mo9311a(objArr, 0);
    }
}
