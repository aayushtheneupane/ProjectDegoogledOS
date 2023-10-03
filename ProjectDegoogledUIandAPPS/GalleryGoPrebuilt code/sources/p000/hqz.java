package p000;

import java.util.AbstractCollection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: hqz */
/* compiled from: PG */
class hqz extends AbstractCollection implements Collection {

    /* renamed from: a */
    public final Object f13284a;

    /* renamed from: b */
    public java.util.Collection f13285b;

    /* renamed from: c */
    public final hqz f13286c;

    /* renamed from: d */
    public final /* synthetic */ hrc f13287d;

    /* renamed from: e */
    private final java.util.Collection f13288e;

    public hqz(hrc hrc, Object obj, java.util.Collection collection, hqz hqz) {
        java.util.Collection collection2;
        this.f13287d = hrc;
        this.f13284a = obj;
        this.f13285b = collection;
        this.f13286c = hqz;
        if (hqz != null) {
            collection2 = hqz.f13285b;
        } else {
            collection2 = null;
        }
        this.f13288e = collection2;
    }

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public Spliterator spliterator() {
        return Collection$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    public final boolean add(Object obj) {
        mo7724a();
        boolean isEmpty = this.f13285b.isEmpty();
        boolean add = this.f13285b.add(obj);
        if (!add) {
            return add;
        }
        hrc.m11942a(this.f13287d);
        if (!isEmpty) {
            return add;
        }
        mo7728c();
        return true;
    }

    public final boolean addAll(java.util.Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.f13285b.addAll(collection);
        if (!addAll) {
            return addAll;
        }
        hrc.m11943a(this.f13287d, this.f13285b.size() - size);
        if (size != 0) {
            return addAll;
        }
        mo7728c();
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo7728c() {
        hqz hqz = this.f13286c;
        if (hqz == null) {
            this.f13287d.f13294a.put(this.f13284a, this.f13285b);
        } else {
            hqz.mo7728c();
        }
    }

    public final void clear() {
        int size = size();
        if (size != 0) {
            this.f13285b.clear();
            hrc.m11945b(this.f13287d, size);
            mo7727b();
        }
    }

    public final boolean contains(Object obj) {
        mo7724a();
        return this.f13285b.contains(obj);
    }

    public final boolean containsAll(java.util.Collection collection) {
        mo7724a();
        return this.f13285b.containsAll(collection);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        mo7724a();
        return this.f13285b.equals(obj);
    }

    public final int hashCode() {
        mo7724a();
        return this.f13285b.hashCode();
    }

    public final Iterator iterator() {
        mo7724a();
        return new hqy(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo7724a() {
        java.util.Collection collection;
        hqz hqz = this.f13286c;
        if (hqz != null) {
            hqz.mo7724a();
            if (this.f13286c.f13285b != this.f13288e) {
                throw new ConcurrentModificationException();
            }
        } else if (this.f13285b.isEmpty() && (collection = (java.util.Collection) this.f13287d.f13294a.get(this.f13284a)) != null) {
            this.f13285b = collection;
        }
    }

    public final boolean remove(Object obj) {
        mo7724a();
        boolean remove = this.f13285b.remove(obj);
        if (remove) {
            hrc.m11944b(this.f13287d);
            mo7727b();
        }
        return remove;
    }

    public final boolean removeAll(java.util.Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.f13285b.removeAll(collection);
        if (removeAll) {
            hrc.m11943a(this.f13287d, this.f13285b.size() - size);
            mo7727b();
        }
        return removeAll;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo7727b() {
        hqz hqz = this.f13286c;
        if (hqz != null) {
            hqz.mo7727b();
        } else if (this.f13285b.isEmpty()) {
            this.f13287d.f13294a.remove(this.f13284a);
        }
    }

    public final boolean retainAll(java.util.Collection collection) {
        ife.m12898e((Object) collection);
        int size = size();
        boolean retainAll = this.f13285b.retainAll(collection);
        if (retainAll) {
            hrc.m11943a(this.f13287d, this.f13285b.size() - size);
            mo7727b();
        }
        return retainAll;
    }

    public final int size() {
        mo7724a();
        return this.f13285b.size();
    }

    public final String toString() {
        mo7724a();
        return this.f13285b.toString();
    }
}
