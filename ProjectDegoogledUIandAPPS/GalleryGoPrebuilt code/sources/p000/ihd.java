package p000;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.RandomAccess;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.List;
import p003j$.util.List$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.function.UnaryOperator;
import p003j$.util.stream.Stream;

/* renamed from: ihd */
/* compiled from: PG */
abstract class ihd extends AbstractList implements List, Collection, ije {

    /* renamed from: a */
    public boolean f14175a = true;

    /* renamed from: a */
    public final boolean mo8521a() {
        return this.f14175a;
    }

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public final void replaceAll(UnaryOperator unaryOperator) {
        List$$CC.replaceAll$$dflt$$(this, unaryOperator);
    }

    public final void sort(Comparator comparator) {
        List$$CC.sort$$dflt$$(this, comparator);
    }

    public final Spliterator spliterator() {
        return List$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    public void add(int i, Object obj) {
        mo8527c();
        super.add(i, obj);
    }

    public boolean add(Object obj) {
        mo8527c();
        return super.add(obj);
    }

    public boolean addAll(int i, java.util.Collection collection) {
        mo8527c();
        return super.addAll(i, collection);
    }

    public boolean addAll(java.util.Collection collection) {
        mo8527c();
        return super.addAll(collection);
    }

    public void clear() {
        mo8527c();
        super.clear();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final void mo8527c() {
        if (!this.f14175a) {
            throw new UnsupportedOperationException();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof java.util.List) {
            if (!(obj instanceof RandomAccess)) {
                return super.equals(obj);
            }
            java.util.List list = (java.util.List) obj;
            int size = size();
            if (size == list.size()) {
                for (int i = 0; i < size; i++) {
                    if (!get(i).equals(list.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    /* renamed from: b */
    public final void mo8526b() {
        this.f14175a = false;
    }

    public Object remove(int i) {
        mo8527c();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        mo8527c();
        return super.remove(obj);
    }

    public final boolean removeAll(java.util.Collection collection) {
        mo8527c();
        return super.removeAll(collection);
    }

    public final boolean retainAll(java.util.Collection collection) {
        mo8527c();
        return super.retainAll(collection);
    }

    public Object set(int i, Object obj) {
        mo8527c();
        return super.set(i, obj);
    }
}
