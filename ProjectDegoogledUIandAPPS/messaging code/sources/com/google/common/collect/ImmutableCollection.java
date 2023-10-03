package com.google.common.collect;

import com.google.common.collect.ImmutableList;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;

public abstract class ImmutableCollection extends AbstractCollection implements Serializable {

    /* renamed from: HP */
    private transient ImmutableList f2435HP;

    ImmutableCollection() {
    }

    /* renamed from: Ol */
    public ImmutableList mo8648Ol() {
        ImmutableList immutableList = this.f2435HP;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList Pl = mo8702Pl();
        this.f2435HP = Pl;
        return Pl;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Pl */
    public ImmutableList mo8702Pl() {
        int size = size();
        if (size == 0) {
            return ImmutableList.m4204ql();
        }
        if (size != 1) {
            return new RegularImmutableAsList(this, toArray());
        }
        return ImmutableList.m4203of(iterator().next());
    }

    @Deprecated
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean contains(Object obj) {
        return obj != null && super.contains(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8652d(Object[] objArr, int i) {
        C1692rb it = iterator();
        while (it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
        return i;
    }

    public abstract C1692rb iterator();

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public abstract boolean mo8636pl();

    @Deprecated
    public final boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    public final Object[] toArray() {
        int size = size();
        if (size == 0) {
            return C1638_a.EMPTY_ARRAY;
        }
        Object[] objArr = new Object[size];
        mo8652d(objArr, 0);
        return objArr;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new ImmutableList.SerializedForm(toArray());
    }

    public final Object[] toArray(Object[] objArr) {
        if (objArr != null) {
            int size = size();
            if (objArr.length < size) {
                objArr = C1638_a.m4556c(objArr, size);
            } else if (objArr.length > size) {
                objArr[size] = null;
            }
            mo8652d(objArr, 0);
            return objArr;
        }
        throw new NullPointerException();
    }
}
