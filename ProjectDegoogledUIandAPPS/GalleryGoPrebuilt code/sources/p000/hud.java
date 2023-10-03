package p000;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.List;
import p003j$.util.List$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.function.UnaryOperator;
import p003j$.util.stream.Stream;

/* renamed from: hud */
/* compiled from: PG */
public class hud extends AbstractList implements List, Collection {

    /* renamed from: a */
    public final java.util.List f13410a;

    public hud(java.util.List list) {
        this.f13410a = (java.util.List) ife.m12898e((Object) list);
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

    public final void add(int i, Object obj) {
        this.f13410a.add(mo8030a(i), obj);
    }

    public final void clear() {
        this.f13410a.clear();
    }

    public final Object get(int i) {
        return this.f13410a.get(m12144b(i));
    }

    public final Iterator iterator() {
        return listIterator();
    }

    public final ListIterator listIterator(int i) {
        return new huc(this, this.f13410a.listIterator(mo8030a(i)));
    }

    public final Object remove(int i) {
        return this.f13410a.remove(m12144b(i));
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        subList(i, i2).clear();
    }

    /* renamed from: b */
    private final int m12144b(int i) {
        int size = size();
        ife.m12873b(i, size);
        return (size - 1) - i;
    }

    /* renamed from: a */
    public final int mo8030a(int i) {
        int size = size();
        ife.m12888c(i, size);
        return size - i;
    }

    public final Object set(int i, Object obj) {
        return this.f13410a.set(m12144b(i), obj);
    }

    public final int size() {
        return this.f13410a.size();
    }

    public final java.util.List subList(int i, int i2) {
        ife.m12874b(i, i2, size());
        return ife.m12836a(this.f13410a.subList(mo8030a(i2), mo8030a(i)));
    }
}
