package p000;

import java.util.Collection;
import java.util.Iterator;
import p003j$.util.Collection$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: kz */
/* compiled from: PG */
final class C0302kz implements Collection, p003j$.util.Collection {

    /* renamed from: a */
    private final /* synthetic */ C0304la f15177a;

    public C0302kz(C0304la laVar) {
        this.f15177a = laVar;
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

    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        this.f15177a.mo9192c();
    }

    public final boolean contains(Object obj) {
        return this.f15177a.mo9190b(obj) >= 0;
    }

    public final boolean containsAll(Collection collection) {
        for (Object contains : collection) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public final boolean isEmpty() {
        return this.f15177a.mo9184a() == 0;
    }

    public final Iterator iterator() {
        return new C0298kv(this.f15177a, 1);
    }

    public final boolean remove(Object obj) {
        int b = this.f15177a.mo9190b(obj);
        if (b < 0) {
            return false;
        }
        this.f15177a.mo9188a(b);
        return true;
    }

    public final boolean removeAll(Collection collection) {
        int a = this.f15177a.mo9184a();
        int i = 0;
        boolean z = false;
        while (i < a) {
            if (collection.contains(this.f15177a.mo9186a(i, 1))) {
                this.f15177a.mo9188a(i);
                i--;
                a--;
                z = true;
            }
            i++;
        }
        return z;
    }

    public final boolean retainAll(Collection collection) {
        int a = this.f15177a.mo9184a();
        int i = 0;
        boolean z = false;
        while (i < a) {
            if (!collection.contains(this.f15177a.mo9186a(i, 1))) {
                this.f15177a.mo9188a(i);
                i--;
                a--;
                z = true;
            }
            i++;
        }
        return z;
    }

    public final int size() {
        return this.f15177a.mo9184a();
    }

    public final Object[] toArray() {
        return this.f15177a.mo9312b(1);
    }

    public final Object[] toArray(Object[] objArr) {
        return this.f15177a.mo9311a(objArr, 1);
    }
}
