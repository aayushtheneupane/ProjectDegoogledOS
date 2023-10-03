package p000;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import p003j$.util.List$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.UnaryOperator;

/* renamed from: hrb */
/* compiled from: PG */
class hrb extends hqz implements List, p003j$.util.List {

    /* renamed from: e */
    public final /* synthetic */ hrc f13293e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public hrb(hrc hrc, Object obj, List list, hqz hqz) {
        super(hrc, obj, list, hqz);
        this.f13293e = hrc;
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

    public final void add(int i, Object obj) {
        mo7724a();
        boolean isEmpty = this.f13285b.isEmpty();
        mo7751d().add(i, obj);
        hrc.m11942a(this.f13293e);
        if (isEmpty) {
            mo7728c();
        }
    }

    public final boolean addAll(int i, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = mo7751d().addAll(i, collection);
        if (!addAll) {
            return addAll;
        }
        hrc.m11943a(this.f13293e, this.f13285b.size() - size);
        if (size != 0) {
            return addAll;
        }
        mo7728c();
        return true;
    }

    public final Object get(int i) {
        mo7724a();
        return mo7751d().get(i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final List mo7751d() {
        return (List) this.f13285b;
    }

    public final int indexOf(Object obj) {
        mo7724a();
        return mo7751d().indexOf(obj);
    }

    public final int lastIndexOf(Object obj) {
        mo7724a();
        return mo7751d().lastIndexOf(obj);
    }

    public final ListIterator listIterator() {
        mo7724a();
        return new hra(this);
    }

    public final ListIterator listIterator(int i) {
        mo7724a();
        return new hra(this, i);
    }

    public final Object remove(int i) {
        mo7724a();
        Object remove = mo7751d().remove(i);
        hrc.m11944b(this.f13293e);
        mo7727b();
        return remove;
    }

    public final Object set(int i, Object obj) {
        mo7724a();
        return mo7751d().set(i, obj);
    }

    public final List subList(int i, int i2) {
        mo7724a();
        hrc hrc = this.f13293e;
        Object obj = this.f13284a;
        List subList = mo7751d().subList(i, i2);
        hqz hqz = this.f13286c;
        if (hqz == null) {
            hqz = this;
        }
        return hrc.mo7762a(obj, subList, hqz);
    }
}
