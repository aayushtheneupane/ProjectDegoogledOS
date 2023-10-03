package com.google.common.collect;

import com.google.common.base.C1508E;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;

public abstract class ImmutableSortedSet extends ImmutableSortedSetFauxverideShim implements NavigableSet, C1674lb {

    /* renamed from: MP */
    private static final ImmutableSortedSet f2442MP = new EmptyImmutableSortedSet(f2443xN);

    /* renamed from: xN */
    private static final Comparator f2443xN = C1644bb.m4563zl();

    /* renamed from: bO */
    transient ImmutableSortedSet f2444bO;
    final transient Comparator comparator;

    class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Comparator comparator;
        final Object[] elements;

        public SerializedForm(Comparator comparator2, Object[] objArr) {
            this.comparator = comparator2;
            this.elements = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            C1628V v = new C1628V(this.comparator);
            v.mo8823d(this.elements);
            return v.build();
        }
    }

    ImmutableSortedSet(Comparator comparator2) {
        this.comparator = comparator2;
    }

    /* renamed from: a */
    static ImmutableSortedSet m4245a(Comparator comparator2, int i, Object... objArr) {
        if (i == 0) {
            return m4246c(comparator2);
        }
        C1638_a.m4554b(objArr, i);
        Arrays.sort(objArr, 0, i, comparator2);
        int i2 = 1;
        for (int i3 = 1; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (comparator2.compare(obj, objArr[i2 - 1]) != 0) {
                objArr[i2] = obj;
                i2++;
            }
        }
        Arrays.fill(objArr, i2, i, (Object) null);
        return new RegularImmutableSortedSet(ImmutableList.m4201e(objArr, i2), comparator2);
    }

    /* renamed from: c */
    static ImmutableSortedSet m4246c(Comparator comparator2) {
        if (f2443xN.equals(comparator2)) {
            return f2442MP;
        }
        return new EmptyImmutableSortedSet(comparator2);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Sl */
    public ImmutableSortedSet mo8624Sl() {
        return new DescendingImmutableSortedSet(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract ImmutableSortedSet mo8625a(Object obj, boolean z);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract ImmutableSortedSet mo8626a(Object obj, boolean z, Object obj2, boolean z2);

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public abstract ImmutableSortedSet mo8627b(Object obj, boolean z);

    public Object ceiling(Object obj) {
        return C1652ea.m4577b(tailSet(obj, true).iterator(), (Object) null);
    }

    public Comparator comparator() {
        return this.comparator;
    }

    public abstract C1692rb descendingIterator();

    public Object first() {
        return iterator().next();
    }

    public Object floor(Object obj) {
        return C1652ea.m4577b(headSet(obj, true).descendingIterator(), (Object) null);
    }

    public Object higher(Object obj) {
        return C1652ea.m4577b(tailSet(obj, false).iterator(), (Object) null);
    }

    /* access modifiers changed from: package-private */
    public abstract int indexOf(Object obj);

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public int mo8782j(Object obj, Object obj2) {
        return this.comparator.compare(obj, obj2);
    }

    public Object last() {
        return descendingIterator().next();
    }

    public Object lower(Object obj) {
        return C1652ea.m4577b(headSet(obj, false).descendingIterator(), (Object) null);
    }

    @Deprecated
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this.comparator, toArray());
    }

    public ImmutableSortedSet descendingSet() {
        ImmutableSortedSet immutableSortedSet = this.f2444bO;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet Sl = mo8624Sl();
        this.f2444bO = Sl;
        Sl.f2444bO = this;
        return Sl;
    }

    public ImmutableSortedSet headSet(Object obj) {
        return headSet(obj, false);
    }

    public ImmutableSortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    public ImmutableSortedSet tailSet(Object obj) {
        return tailSet(obj, true);
    }

    public ImmutableSortedSet headSet(Object obj, boolean z) {
        if (obj != null) {
            return mo8625a(obj, z);
        }
        throw new NullPointerException();
    }

    public ImmutableSortedSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj2 != null) {
            C1508E.checkArgument(this.comparator.compare(obj, obj2) <= 0);
            return mo8626a(obj, z, obj2, z2);
        } else {
            throw new NullPointerException();
        }
    }

    public ImmutableSortedSet tailSet(Object obj, boolean z) {
        if (obj != null) {
            return mo8627b(obj, z);
        }
        throw new NullPointerException();
    }
}
